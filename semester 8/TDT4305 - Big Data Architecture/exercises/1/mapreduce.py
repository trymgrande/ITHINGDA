# 2.3.1
import random
import functools
import operator

integers = random.sample(range(-100, 100), 10)
# a

print(integers)

print(functools.reduce(operator.add, integers))
