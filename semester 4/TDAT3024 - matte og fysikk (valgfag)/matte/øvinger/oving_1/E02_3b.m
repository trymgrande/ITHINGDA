%convert 1/3(10) to binary

n = 5; %base 10 input number
r = -1; %rest
output = "";
while n ~= 0
    r = mod(n,2);
    output = append(output, int2str(r));
    n = n/2;
end
disp(reverse(output));