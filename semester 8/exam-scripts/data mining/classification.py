from math import log2

def gini(arr):
    # in case of zero division
    sum = arr[0] + arr[1]
    if sum == 0:
        return 1
        
    return 1 - pow((arr[0]/sum), 2) - pow((arr[1]/sum), 2)

def entropy(arr):
    sum = arr[0] + arr[1]
    # in case of zero division
    if sum == 0:
        return 0

    # in case of or log(0)
    if arr[0] == 0:
        return - (arr[1]/sum) * log2(arr[1]/sum)
    if arr[1] == 0:
        return - (arr[0]/sum) * log2(arr[0]/sum)

    return -(arr[0]/sum) * log2(arr[0]/sum) - (arr[1]/sum) * log2(arr[1]/sum)

def print_gini(arr1, arr2):
    print(f"point {arr1} and {arr2}")
    resstr = "GINI1: "
    gini1 = gini(arr1)
    gini2 = gini(arr2)
    resgini = ((sum(arr1)/(sum(arr1) + sum(arr2))) * gini1) + ((sum(arr2)/(sum(arr1) + sum(arr2))) * gini2)
    resstr += "1 - (" + str(arr1[0]) + "/" + str((arr1[0]+arr1[1])) + ")² - (" + str(arr1[1]) + "/" + str((arr1[0]+arr1[1])) + ")² = " + str(gini1) + "\n"

    resstr += "GINI2: 1 - (" + str(arr2[0]) + "/" + str((arr2[0]+arr2[1])) + ")² - (" + str(arr2[1]) + "/" + str((arr2[0]+arr2[1])) + ")² = " + str(gini2) + "\n"

    resstr += "GINI: (" + str(sum(arr1)) + "/" + str(sum(arr1) + sum(arr2)) + ") * " + str(gini1) + " + (" + str(sum(arr2)) + "/" + str(sum(arr1) + sum(arr2)) + ") * " + str(gini2) + " = " + str(resgini)
    return resstr

def print_entropy(arr1, arr2):
    print(f"point {arr1} and {arr2}")
    resstr = "Entropy1: "
    entropy1 = entropy(arr1)
    entropy2 = entropy(arr2)

    resentropy = ((sum(arr1)/(sum(arr1) + sum(arr2))) * entropy1) + ((sum(arr2)/(sum(arr1) + sum(arr2))) * entropy2)
    resstr += "- ((" + str(arr1[0]) + "/" + str((arr1[0]+arr1[1])) + ") * log2(" + str(arr1[0]) + "/" + str((arr1[0]+arr1[1])) + ")) - ((" + str(arr1[1]) + "/" + str((arr1[0]+arr1[1])) + ") * log2(" + str(arr1[1]) + "/" + str((arr1[0]+arr1[1])) + ")) = " + str(entropy1) + "\n"

    resstr += "Entropy2: - ((" + str(arr2[0]) + "/" + str((arr2[0]+arr2[1])) + ") * log2(" + str(arr2[0]) + "/" + str((arr2[0]+arr2[1])) + ")) - ((" + str(arr2[1]) + "/" + str((arr2[0]+arr2[1])) + ") * log2(" + str(arr2[1]) + "/" + str((arr2[0]+arr2[1])) + ")) = " + str(entropy2) + "\n"

    resstr += "Entropy: (" + str(sum(arr1)) + "/" + str(sum(arr1) + sum(arr2)) + ") * " + str(entropy1) + " + (" + str(sum(arr2)) + "/" + str(sum(arr1) + sum(arr2)) + ") * " + str(entropy2) + " = " + str(resentropy)
    return resstr


#print(gini([2,2]))
#print(entropy([2,4]))
#print(print_gini([3,1], [1,4])+ "\n")
#print(print_entropy([1,2], [2,3]))
print(print_gini([2,0], [1,1]))



