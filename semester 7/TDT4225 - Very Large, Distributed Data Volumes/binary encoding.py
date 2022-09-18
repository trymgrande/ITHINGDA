# 1. Del opp stringen i usortert array = unsorted_array
# 2. Bruk usortert array for å lage bit-stringen for hvert element i unsorted_array
# 3. Lagre bit-stringen i dictionary: dict = {73: "0000100110", 87: "100001000"} osv
# 4. Sorter dict på int-key
# 5. Print dict
import numpy as np


def make_bit_string_dict(unsorted_array):
    bit_string_dict = {}
    distinct_unsorted_array = np.unique(unsorted_array)
    for num in distinct_unsorted_array:
        bit_string = ""
        for i in range(len(unsorted_array)):
            if unsorted_array[i] != num:
                bit_string += "0"
            else:
                bit_string += "1"
        bit_string_dict[num] = bit_string
    return bit_string_dict


def print_bit_string_dict(bit_string_dict):
    string_to_print = ""
    for key, value in bit_string_dict.items():
        string_to_print += key + ' : ' + value + '\n'
    print(string_to_print)


def bitmap(input_string):
    unsorted_array = str.split(input_string)
    bit_string_dict = make_bit_string_dict(unsorted_array)
    print_bit_string_dict(bit_string_dict)
    runlength_encoding(bit_string_dict)


def runlength_encoding(bit_string_dict):
    for key in bit_string_dict:
        bit_string = bit_string_dict[key]

        compressed = []
        if bit_string[0] == '1':
            compressed.append(0)
        count = 1
        char = bit_string[0]
        for i in range(1, len(bit_string)):
            if bit_string[i] == char:
                count = count + 1
            else:
                compressed.append(count)
                char = bit_string[i]
                count = 1
        compressed.append(count)
        if len(compressed) % 2 != 0:
            del compressed[-1]
        string_compressed = [str(element) for element in compressed]
        string_to_print = ", ".join(string_compressed)
        print(str(key) + ': ' + string_to_print)


if __name__ == '__main__':
    input_string = "73 73 87 87 87 63 63 63 31 34 34 34 34 88 88 31 33"
    bitmap(input_string)