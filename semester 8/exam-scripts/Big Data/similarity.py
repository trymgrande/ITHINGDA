import numpy as np

def cosine_similarity(arr1, arr2, PRINT_NAME=False):
    if PRINT_NAME: print("cosine:")
    numerator = 0
    arr1_vector_length_squared = 0
    arr2_vector_length_squared = 0
    for i, x in enumerate(arr1):
        numerator += x*arr2[i]
        arr1_vector_length_squared += pow(x,2)
        arr2_vector_length_squared += pow(arr2[i], 2)

    return round((numerator/(np.sqrt(arr1_vector_length_squared)*np.sqrt(arr2_vector_length_squared))),3)


def mean(arr):
    return sum(arr)/len(arr)

def print_pearson_similarity(arr1, arr2, array_id_1=1, array_id_2=2, PRINT_NAME=False):
    """
    use -1 for unknown values, any number for known values
    """
    if PRINT_NAME: print("pearson:")
    arr1_ignored = [x for x in arr1 if x!=-1]
    arr2_ignored = [x for x in arr2 if x!=-1]

    m1 = mean(arr1_ignored)
    m2 = mean(arr2_ignored)

    print(f"m{array_id_1}=sum({arr1_ignored})/{len(arr1_ignored)}={m1}")
    print(f"m{array_id_2}=sum({arr2_ignored})/{len(arr2_ignored)}={m2}")

    r1 = [x-m1 if x!=-1 else 0 for x in arr1]
    r2 = [x-m2 if x!=-1 else 0 for x in arr2]

    r1_calculation = f"r{array_id_1}={[f'{x}-{m1}' if x!=-1 else 0 for x in arr1]}"
    r1_calculation_without_quotes = r1_calculation.replace("'", "")
    print(r1_calculation_without_quotes)

    r2_calculation = f"r{array_id_2}={[f'{x}-{m2}' if x!=-1 else 0 for x in arr2]}"
    r2_calculation_without_quotes = r2_calculation.replace("'", "")
    print(r2_calculation_without_quotes)

    print(f"cos(r{array_id_1},r{array_id_2})={cosine_similarity(r1, r2)}")


def print_jaccard_similarity(arr1, arr2, PRINT_NAME=False):
    """
    use number <=0 for negative/unknown values, >0 for known/positive values
    """
    if PRINT_NAME: print("jaccard:")
    numerator = sum([1 if (x>0 and y>0) else 0 for x,y in zip(arr1,arr2)])
    denominator = sum([1 if (x>0 or y>0) else 0 for x,y in zip(arr1,arr2)])
    answer = round(numerator/denominator, 3)

    print(f"({[1 if (x>0 and y>0) else 0 for x,y in zip(arr1,arr2)]})/({[1 if (x>0 or y>0) else 0 for x,y in zip(arr1,arr2)]})={numerator}/{denominator}={answer}")



arr1 = [-1,0,0,0,1]
arr2 = [0,1,1,1,0]

print(cosine_similarity(arr1, arr2, True))

print("-----------------------------")

arr1 = [1, 2, 2, 3, 3]
arr2 = [1, 0, 1, 2, 3]

arr_id_1 = 4
arr_id_2 = 5

print_pearson_similarity(arr1, arr2, arr_id_1, arr_id_2, True)

print("-----------------------------")


s0 = [0,0,0,1,0,1]
s1 = [0,0,2,4,0,2]
s2 = [2,2,0,4,2,0]
s3 = [2,2,0,4,2,0]
s4 = [2,2,0,3,0,0]
s5 = [2,2,0,3,0,0]


print_jaccard_similarity(s0, s2, True)