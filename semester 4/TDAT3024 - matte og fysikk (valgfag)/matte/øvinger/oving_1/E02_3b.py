#convert 1/3 decimal to binary

n = 5
r = -1
output = ""
while n != 0:
    r = n % 2
    output += (str(r))
    n = n / 2
print(output)