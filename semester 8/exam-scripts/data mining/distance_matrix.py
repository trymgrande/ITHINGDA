import numpy as np

# 0 is x, 1 is y
def euclidean_distance(arr1, arr2):
    return round(np.sqrt(abs(pow(arr2[0] - arr1[0], 2)) + abs(pow(arr2[1] - arr1[1], 2))), 2)

def manhattan_distance(arr1, arr2):
    return round(abs(arr1[0] - arr2[0]) + abs(arr1[1] - arr2[1]), 2)

def l_min(arr1, arr2):
    return min([abs(arr1[0] - arr2[0]), abs(arr1[1] - arr2[1])])

def print_matrix(arr, dist_func):
    resstring = ""
    for i, element in enumerate(arr):
        resstring += str(element) + ": "
        for element2 in arr:
            if dist_func == "euclidean": 
                resstring += str(euclidean_distance(element, element2)) + ",  "
            elif dist_func == "manhattan":
                resstring += str(manhattan_distance(element, element2)) + ",  "
        resstring += "\n"
    print(resstring)

def print_symmetric_matrix(arr, dist_func):
    resstring = ""
    for i, element in enumerate(arr):
        resstring += str(element) + ": "
        for j in range(i):
            if dist_func == "euclidean": 
                resstring += str(euclidean_distance(element, arr[j])) + ",  "
            elif dist_func == "manhattan":
                resstring += str(manhattan_distance(element, arr[j])) + ",  "
        resstring += "\n"
    print(resstring)

def print_symmetric_matrix_markdown(arr, dist_func):
    ALPHABET = "abcdefghijklmnopqrstuvwxyz"
    # ALPHABET = ['x1','x2','x3','x4','x5','c1','c2']
    result_string = ""
    # header
    result_string += '||'
    for i in range(len(arr)):
        result_string += ALPHABET[i] + '|'
    result_string += f"\n {'|---' * (len(arr)+1)}|\n"
    # matrix
    for i, element in enumerate(arr):
        result_string += f"|{ALPHABET[i]}|"
        for j in range(i):
            if dist_func == "euclidean": 
                result_string += str(euclidean_distance(element, arr[j])) + "|"
            elif dist_func == "manhattan":
                result_string += str(manhattan_distance(element, arr[j])) + "|"
            elif dist_func == "l_min":
                result_string += str(l_min(element, arr[j])) + "|"

        result_string += "|\n"
    print(result_string)


points1 = [[14,1],[1,8], [3,12], [5,1], [13,11], [12,6], [4,12], [1,8], [8,3], [5,1], [14,12], [12, 9], [4,5], [8,4], [2,3]]
points2 = [[5,8], [6,7], [6,5], [2,4], [3,4], [5,4], [7,4], [9,4], [3,3], [8,2], [7,5]]
points3 = [[5,8],[6,7],[6,5],[2,4],[3,4],[5,4],[7,4],[9,4],[3,3],[8,2],[7,5]] # exam 21-05
points4 = [[0,2],[0,0],[1.5,0],[5,0],[5,2],[0,0],[5,1]] # k-means exam 21-08
points5 = [[5,8],[6,7],[6,5],[2,4],[3,4],[5,4],[7,4],[9,4],[3,3],[8,2],[7,5]]

DISTANCE = "l_min" # manhattan/euclidean/l_min
DATA = points5

print_matrix(DATA, DISTANCE)
print_symmetric_matrix(DATA, DISTANCE)
print_symmetric_matrix_markdown(DATA, DISTANCE)
