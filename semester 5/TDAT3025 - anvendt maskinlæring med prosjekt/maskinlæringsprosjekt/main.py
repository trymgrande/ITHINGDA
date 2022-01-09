import cv2
import numpy as np
import matplotlib.pyplot as plt
from math import sqrt
from skimage.feature import blob_dog, blob_log, blob_doh
import imutils
import argparse
import os
import math

from classification import training, getLabel, SIGNS

GENERATE_NEW_DATASET = False

### Preprocess image
def constrast(image):
    img_hist_equalized = cv2.cvtColor(image, cv2.COLOR_BGR2YCrCb)
    channels = cv2.split(img_hist_equalized)
    channels[0] = cv2.equalizeHist(channels[0])
    img_hist_equalized = cv2.merge(channels)
    img_hist_equalized = cv2.cvtColor(img_hist_equalized, cv2.COLOR_YCrCb2BGR)
    return img_hist_equalized

def laplacian_of_gaussian(image):
    laplacian_image = cv2.GaussianBlur(image, (3,3), 0)             # paramter
    gray_image = cv2.cvtColor(laplacian_image, cv2.COLOR_BGR2GRAY)
    laplacian_image = cv2.Laplacian( gray_image, cv2.CV_8U,3,3,2)   # parameter
    laplacian_image = cv2.convertScaleAbs(laplacian_image)
    return laplacian_image
    
def binarization(image):
    bin = cv2.threshold(image,32,255,cv2.THRESH_BINARY)[1]
    return bin

def pre_processing(image):
    image = constrast(image)
    image = laplacian_of_gaussian(image)
    image = binarization(image)
    return image

# Find Signs
def remove_small_components(image, threshold):
    # find all your connected components (white blobs in your image)
    nb_components, output, stats, centroids = cv2.connectedComponentsWithStats(image, connectivity=8)
    sizes = stats[1:, -1]; nb_components = nb_components - 1

    img = np.zeros((output.shape),dtype = np.uint8)
    # for every component in the image, you keep it only if it's above threshold
    for i in range(0, nb_components):
        if sizes[i] >= threshold:
            img[output == i + 1] = 255
    return img

def find_contours(image):
    # find contours in the thresholded image
    contours = cv2.findContours(image, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    contours = contours[0] if imutils.is_cv2() else contours[1]
    return contours

def contour_is_sign(contour, centroid, threshold):
    # perimeter, centroid, threshold
    # Compute signature of contour
    result=[]
    for c in contour:
        c = c[0]
        dist = sqrt((c[0] - centroid[0])**2 + (c[1] - centroid[1])**2)
        result.append(dist)
    max_value = max(result)
    signature = [float(dist) / max_value for dist in result]
    # Check signature of contour.
    temp = sum((1 - s) for s in signature)
    temp = temp / len(signature)
    if temp < threshold: # is  the sign
        return True, max_value + 2
    else:                 # is not the sign
        return False, max_value + 2

# crop sign 
def crop_sign_contour(image, center, max_distance):
    width = image.shape[1]
    height = image.shape[0]
    top = max([int(center[0] - max_distance), 0])
    bottom = min([int(center[0] + max_distance + 1), height-1])
    left = max([int(center[1] - max_distance), 0])
    right = min([int(center[1] + max_distance+1), width-1])
    print(left, right, top, bottom)
    return image[left:right, top:bottom]

def crop_sign(image, coordinate):
    width = image.shape[1]
    height = image.shape[0]
    top = max([int(coordinate[0][1]), 0])
    bottom = min([int(coordinate[1][1]), height-1])
    left = max([int(coordinate[0][0]), 0])
    right = min([int(coordinate[1][0]), width-1])
    #print(top,left,bottom,right)
    return image[top:bottom,left:right]


def find_biggest_sign(image, contours, threshold, distanceThreshold):
    max_distance = 0
    coordinate = None
    sign = None
    for c in contours:
        M = cv2.moments(c)
        if M["m00"] == 0:
            continue
        cX = int(M["m10"] / M["m00"])
        cY = int(M["m01"] / M["m00"])
        isSign, distance = contour_is_sign(c, [cX, cY], 1 - threshold)
        if isSign and distance > max_distance and distance > distanceThreshold:
            max_distance = distance
            coordinate = np.reshape(c, [-1,2])
            left, top = np.amin(coordinate, axis=0)
            right, bottom = np.amax(coordinate, axis = 0)
            coordinate = [(left-2,top-2),(right+3,bottom+1)]
            sign = crop_sign(image, coordinate)
    return sign, coordinate


def find_signs(image, contours, threshold, distanceThreshold):
    signs = []
    coordinates = []
    for c in contours:
        # compute the center of the contour
        M = cv2.moments(c)
        if M["m00"] == 0:
            continue
        cX = int(M["m10"] / M["m00"])
        cY = int(M["m01"] / M["m00"])
        isSign, maxDist = contour_is_sign(c, [cX, cY], 1 - threshold)
        if isSign and maxDist > distanceThreshold:
            sign = crop_sign_contour(image, [cX, cY], maxDist)
            signs.append(sign)
            coordinate = np.reshape(c, [-1,2])
            top, left = np.amin(coordinate, axis=0)
            right, bottom = np.amax(coordinate, axis = 0)
            coordinates.append([(top-2,left-2),(right+1,bottom+1)])
    return signs, coordinates

def localization(image, min_size_components, similitary_contour_with_circle, model, current_sign_type, count):
    original_image = image.copy()
    processed_image = pre_processing(image)

    processed_image = remove_small_components(processed_image, min_size_components)

    processed_image = cv2.bitwise_and(processed_image, processed_image, mask=remove_unnessesary_colors(image))

    #processed_image = remove_line(processed_image)

    cv2.imshow('BINARY IMAGE', processed_image)
    contours = find_contours(processed_image)
    #signs, coordinates = findSigns(image, contours, similitary_contour_with_circle, 15)
    sign, coordinate = find_biggest_sign(original_image, contours, similitary_contour_with_circle, 15)
    
    text = ""
    sign_type = -1
    i = 0

    if sign is not None:
        sign_type = getLabel(model, sign)
        text = SIGNS[sign_type]
        if GENERATE_NEW_DATASET:
            # writes detected signs to a new dataset, needs subdirectories to work
            cv2.imwrite('./{}/'+text+'/'+str(count)+text+'.png'.format("dataset_test"), sign)

    if sign_type > 0 and sign_type != current_sign_type:        
        cv2.rectangle(original_image, coordinate[0],coordinate[1], (0, 255, 0), 1)
        font = cv2.FONT_HERSHEY_PLAIN
        cv2.putText(original_image,text,(coordinate[0][0], coordinate[0][1] -15), font, 1,(0,0,255),2,cv2.LINE_4)
    return coordinate, original_image, sign_type, text

def remove_line(img):
    gray_image = img.copy()
    edges = cv2.Canny(gray_image,50,150,apertureSize = 3)
    MIN_LINE_LENGTH = 5
    MAX_LINE_GAP = 3
    lines = cv2.HoughLinesP(edges,1,np.pi/180,15,MIN_LINE_LENGTH,MAX_LINE_GAP)
    mask = np.ones(img.shape[:2], dtype="uint8") * 255
    if lines is not None:
        for line in lines:
            for x1,y1,x2,y2 in line:
                cv2.line(mask,(x1,y1),(x2,y2),(0,0,0),2)
    return cv2.bitwise_and(img, img, mask=mask)

def remove_unnessesary_colors(img):
    frame = cv2.GaussianBlur(img, (3,3), 0) 
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    # define range of blue color in HSV
    dark_blue = np.array([100,128,0])
    light_blue = np.array([215,255,255])
    # Threshold the HSV image to get only blue colors
    mask_blue = cv2.inRange(hsv, dark_blue, light_blue)

    dark_white = np.array([0,0,128], dtype=np.uint8)
    light_white = np.array([255,255,255], dtype=np.uint8)
    # Threshold the HSV image to get only blue colors
    mask_white = cv2.inRange(hsv, dark_white, light_white)

    dark_black = np.array([0,0,0], dtype=np.uint8)
    light_black = np.array([170,150,50], dtype=np.uint8)

    mask_black = cv2.inRange(hsv, dark_black, light_black)

    mask_1 = cv2.bitwise_or(mask_blue, mask_white)
    mask = cv2.bitwise_or(mask_1, mask_black)
    # Bitwise-AND mask and original image
    #res = cv2.bitwise_and(frame,frame, mask= mask)
    return mask

def main(args):
    # Training phase
    model = training()

    vidcap = cv2.VideoCapture(args.file_name)

    fps = vidcap.get(cv2.CAP_PROP_FPS)
    width = vidcap.get(3)  # float
    height = vidcap.get(4) # float

    # Define the codec and create VideoWriter object
    fourcc = cv2.VideoWriter_fourcc(*'XVID')
    out = cv2.VideoWriter('output.avi',fourcc, fps , (640,480))

    # initialize the termination criteria for cam shift, indicating
    # a maximum of ten iterations or movement by a least one pixel
    # along with the bounding box of the ROI
    termination = (cv2.TERM_CRITERIA_EPS | cv2.TERM_CRITERIA_COUNT, 10, 1)
    roi_box = None
    roi_hist = None

    success = True
    similitary_contour_with_circle = 0.65   # parameter
    count = 0
    current_sign = None
    current_text = ""
    current_size = 0
    sign_count = 0
    coordinates = []
    position = []
    file = open("Output.txt", "w")
    while True:
        success,frame = vidcap.read()
        if not success:
            print("FINISHED")
            break

        frame = cv2.resize(frame, (640,480))

        print("Frame:{}".format(count))
        #image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
        CHECK_EVERY_NTH_FRAME = 1
        if count % CHECK_EVERY_NTH_FRAME == 0:
            coordinate, image, sign_type, text = localization(frame, args.min_size_components,
                                                              args.similitary_contour_with_circle, model, current_sign,
                                                              count)
            if coordinate is not None:
                cv2.rectangle(image, coordinate[0], coordinate[1], (255, 255, 255), 1)
            print("Sign:{}".format(sign_type))
            if sign_type > 0 and (not current_sign or sign_type != current_sign):
                current_sign = sign_type
                current_text = text
                top = int(coordinate[0][1] * 1.05)
                left = int(coordinate[0][0] * 1.05)
                bottom = int(coordinate[1][1] * 0.95)
                right = int(coordinate[1][0] * 0.95)

                position = [count, sign_type if sign_type <= 8 else 8, coordinate[0][0], coordinate[0][1],
                            coordinate[1][0], coordinate[1][1]]
                cv2.rectangle(image, coordinate[0], coordinate[1], (0, 255, 0), 1)
                font = cv2.FONT_HERSHEY_PLAIN
                cv2.putText(image, text, (coordinate[0][0], coordinate[0][1] - 15), font, 1, (0, 0, 255), 2, cv2.LINE_4)

                tl = [left, top]
                br = [right, bottom]
                print(tl, br)
                current_size = math.sqrt(math.pow((tl[0] - br[0]), 2) + math.pow((tl[1] - br[1]), 2))
                # grab the ROI for the bounding box and convert it
                # to the HSV color space

                try:
                    roi = frame[tl[1]:br[1], tl[0]:br[0]]
                    roi = cv2.cvtColor(roi, cv2.COLOR_BGR2HSV)
                except:
                    print("Something went wrong while processing the frame")

                # compute a HSV histogram for the ROI and store the
                # bounding box
                roi_hist = cv2.calcHist([roi], [0], None, [16], [0, 180])
                roi_hist = cv2.normalize(roi_hist, roi_hist, 0, 255, cv2.NORM_MINMAX)
                roi_box = (tl[0], tl[1], br[0], br[1])

            elif current_sign:
                hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
                back_project = cv2.calcBackProject([hsv], [0], roi_hist, [0, 180], 1)

                # apply cam shift to the back projection, convert the
                # points to a bounding box, and then draw them
                (r, roi_box) = cv2.CamShift(back_project, roi_box, termination)
                points = np.int0(cv2.boxPoints(r))
                sum = points.sum(axis=1)
                tl = points[np.argmin(sum)]
                br = points[np.argmax(sum)]
                size = math.sqrt(pow((tl[0] - br[0]), 2) + pow((tl[1] - br[1]), 2))
                print(size)

                if current_size < 1 or size < 1 or size / current_size > 30 or math.fabs(
                        (tl[0] - br[0]) / (tl[1] - br[1])) > 2 or math.fabs((tl[0] - br[0]) / (tl[1] - br[1])) < 0.5:
                    current_sign = None
                    print("Stop tracking")
                else:
                    current_size = size

                if sign_type > 0:
                    top = int(coordinate[0][1])
                    left = int(coordinate[0][0])
                    bottom = int(coordinate[1][1])
                    right = int(coordinate[1][0])

                    position = [count, sign_type if sign_type <= 8 else 8, left, top, right, bottom]
                    cv2.rectangle(image, coordinate[0], coordinate[1], (0, 255, 0), 1)
                    font = cv2.FONT_HERSHEY_PLAIN
                    cv2.putText(image, text, (coordinate[0][0], coordinate[0][1] - 15), font, 1, (0, 0, 255), 2,
                                cv2.LINE_4)
                elif current_sign:
                    position = [count, sign_type if sign_type <= 8 else 8, tl[0], tl[1], br[0], br[1]]
                    cv2.rectangle(image, (tl[0], tl[1]), (br[0], br[1]), (0, 255, 0), 1)
                    font = cv2.FONT_HERSHEY_PLAIN
                    cv2.putText(image, current_text, (tl[0], tl[1] - 15), font, 1, (0, 0, 255), 2, cv2.LINE_4)

            if current_sign:
                sign_count += 1
                coordinates.append(position)

            cv2.imshow('Result', image)
            count = count + 1
            # Write to video
            out.write(image)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

        count += 1
    file.write("{}".format(sign_count))
    for pos in coordinates:
        file.write("\n{} {} {} {} {} {}".format(pos[0],pos[1],pos[2],pos[3],pos[4], pos[5]))
    print("Finish {} frames".format(count))
    file.close()
    return 


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="NLP Assignment Command Line")
    
    parser.add_argument(
      '--file_name',
      default= "./input.mp4",
      help= "Video to be analyzed"
      )
    
    parser.add_argument(
      '--min_size_components',
      type = int,
      default= 300,
      help= "Min size component to be reserved"
      )

    
    parser.add_argument(
      '--similitary_contour_with_circle',
      type = float,
      default= 0.65,
      help= "Similitary to a circle"
      )
    
    args = parser.parse_args()
    main(args)
