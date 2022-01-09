import cv2
import numpy as np
from matplotlib import pyplot as plt
from os import listdir
# local modules
from common import clock, mosaic

# Parameter
SIZE = 32

DISPLAY_ACCURACY = True

# from main import SIGNS
SIGNS = [
    "UNKNOWN SIGN-LIKE",
    "20",
    "30",
    "40",
    "50",
    "60",
    "70",
    "80",
    "100",
    "120"
]

def load_traffic_dataset(dataset_name):
    dataset = []
    labels = []
    for sign_type in SIGNS:
        sign_list = listdir("./{}/{}".format(dataset_name,sign_type))
        if len(sign_list) == 0:
            break
        for sign_file in sign_list:
            if '.png' in sign_file:
                path = "./{}/{}/{}".format(dataset_name, sign_type,sign_file)                
                print(path)
                img = cv2.imread(path,0)
                img = cv2.resize(img, (SIZE, SIZE))
                img = np.reshape(img, [SIZE, SIZE])
                dataset.append(img)
                labels.append(SIGNS.index(sign_type))
    return np.array(dataset), np.array(labels)
    

def deskew_image(img):
    moments = cv2.moments(img)
    if abs(moments['mu02']) < 1e-2:
        return img.copy()
    skew = moments['mu11']/moments['mu02']
    M = np.float32([[1, skew, -0.5*SIZE*skew], [0, 1, 0]])
    img = cv2.warpAffine(img, M, (SIZE, SIZE), flags=cv2.WARP_INVERSE_MAP | cv2.INTER_LINEAR)
    return img

class StatModel(object):
    def load(self, fn):
        self.model.load(fn)  # Known bug: https://github.com/opencv/opencv/issues/4969
    def save(self, fn):
        self.model.save(fn)

class SVM(StatModel):
    #def __init__(self, C = 12.5, gamma = 0.50625):
    def __init__(self, C = 150, gamma = 0.0001):
        self.model = cv2.ml.SVM_create()
        self.model.setGamma(gamma)
        self.model.setC(C)
        self.model.setKernel(cv2.ml.SVM_LINEAR)
        self.model.setType(cv2.ml.SVM_C_SVC)

    def train(self, samples, responses):
        self.model.train(samples, cv2.ml.ROW_SAMPLE, responses)

    def predict(self, samples):

        return self.model.predict(samples)[1].ravel()


def evaluate_model(model, data, samples, labels):
    resp = model.predict(samples)
    #err = (labels != resp).mean()
    hits, misses = 0, 0
    for i in range(len(resp)):
        if resp[i] == labels[i]:
            hits += 1
        else:
            misses += 1

    print('accuracy: %.2f %%' % ((hits / len(labels))*100))
    # print('error: ', err)
    # print('Accuracy: %.2f %%' % ((1 - err) * 100))

    confusion = np.zeros((10, 10), np.int32)
    for i, j in zip(labels, resp):
        confusion[int(i), int(j)] += 1
    print('confusion matrix:')
    print(confusion, '\n')

    vis = []
    for img, flag in zip(data, resp == labels):
        img = cv2.cvtColor(img, cv2.COLOR_GRAY2BGR)
        if not flag:
            img[..., :2] = 0

        vis.append(img)
    return mosaic(16, vis)


def get_hog() : 
    WIN_SIZE = (20,20)
    BLOCK_SIZE = (10,10)
    BLOCK_STRIDE = (5,5)
    CELL_SIZE = (10,10)
    N_BINS = 9
    DERIV_APERTURE = 1
    WIN_SIGMA = -1.
    HISTOGRAM_NORM_TYPE = 0
    L_2_HYS_THRESHOLD = 0.2
    GAMMA_CORRECTION = 1
    N_LEVELS = 64
    SIGNED_GRADIENT = True

    hog = cv2.HOGDescriptor(WIN_SIZE,BLOCK_SIZE,BLOCK_STRIDE,CELL_SIZE,N_BINS,DERIV_APERTURE,WIN_SIGMA,HISTOGRAM_NORM_TYPE,L_2_HYS_THRESHOLD,GAMMA_CORRECTION,N_LEVELS, SIGNED_GRADIENT)

    return hog

    # shows accuracy and confusion matrix for:
        # dataset trained on dataset
        # test dataset from test video trained on dataset
def evaluate_data(model, data, hog_descriptors_test, labels_test):
    # accuracy for subset of normal dataset
    print("\nevaluating 10%% of dataset, trained on 90%% of dataset:")
    evaluate_model(model, data, hog_descriptors_test, labels_test)



    print('Loading data from dataset_test ... ')
    # Load data.
    #data, labels = load_data('data.png')
    data, labels = load_traffic_dataset("dataset_test")
    print(data.shape)
    print('Shuffle data ... ')
    # Shuffle data
    random = np.random.RandomState(10)
    shuffle = random.permutation(len(data))
    data, labels = data[shuffle], labels[shuffle]
    
    print('Deskew images ... ')
    data_deskewed = list(map(deskew_image, data))
    
    print('Defining HoG parameters ...')
    # HoG feature descriptor
    hog = get_hog()

    print('Calculating HoG descriptor for every image ... ')
    hog_descriptors = []
    for img in data_deskewed:
        hog_descriptors.append(hog.compute(img))
    hog_descriptors = np.squeeze(hog_descriptors)
    
    # accuracy for signs detected in test video
    print("\nevaluating test video, trained on dataset:")
    evaluate_model(model, data, hog_descriptors, labels)

    return


def training():
    print('Loading data from dataset ... ')
    # Load data.
    #data, labels = load_data('data.png')
    data, labels = load_traffic_dataset("dataset")
    print(data.shape)
    print('Shuffle data ... ')
    # Shuffle data
    random = np.random.RandomState(10)
    shuffle = random.permutation(len(data))
    data, labels = data[shuffle], labels[shuffle]
    
    print('Deskew images ... ')
    data_deskewed = list(map(deskew_image, data))
    
    print('Defining HoG parameters ...')
    # HoG feature descriptor
    hog = get_hog()

    print('Calculating HoG descriptor for every image ... ')
    hog_descriptors = []
    for img in data_deskewed:
        hog_descriptors.append(hog.compute(img))
    hog_descriptors = np.squeeze(hog_descriptors)

    print('Spliting data into training (90%) and test set (10%)... ')
    train_n=int(0.9*len(hog_descriptors))
    data_train, data_test = np.split(data_deskewed, [train_n])
    hog_descriptors_train, hog_descriptors_test = np.split(hog_descriptors, [train_n])
    labels_train, labels_test = np.split(labels, [train_n])
    
    
    print('Training SVM model ...')
    model = SVM()
    model.train(hog_descriptors_train, labels_train)

    print('Saving SVM model ...')
    model.save('data_svm.dat')

    if DISPLAY_ACCURACY:
        evaluate_data(model, data, hog_descriptors_test, labels_test)

    return model

def getLabel(model, data):
    gray = cv2.cvtColor(data, cv2.COLOR_BGR2GRAY)
    img = [cv2.resize(gray,(SIZE,SIZE))]
    #print(np.array(img).shape)
    img_deskewed = list(map(deskew_image, img))
    hog = get_hog()
    hog_descriptors = np.array([hog.compute(img_deskewed[0])])
    hog_descriptors = np.reshape(hog_descriptors, [-1, hog_descriptors.shape[1]])
    return int(model.predict(hog_descriptors)[0])

