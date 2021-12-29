import math


def originalFunction(x):
    return (1 - (1 / math.tan(x))) / (math.tan(x)**2)


def simplifiedFunction(x):
    return -1 / ((1 / math.tan(x)) + 1)


print('original function: \t simplified function:')
print()

x = 10**(-1)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-2)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-3)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-4)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-5)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-6)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-7)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-8)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-9)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-10)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-11)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-12)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-13)
print(originalFunction(x), '\t', simplifiedFunction(x))
x=10**(-14)
print(originalFunction(x), '\t', simplifiedFunction(x))
