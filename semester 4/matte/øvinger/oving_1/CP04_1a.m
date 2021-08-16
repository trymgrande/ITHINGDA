function cp0_4_1a()
    format long;
    x = -1:-1:-14;
    x = 10.^x;
    res = ones(14,3);
    res(1:14,1) = x;
    res(1:14,2) = (1-sec(x))./(tan(x).^2);
    res(1:14,3) = 1./(-1-sec(x));
    disp(res);
end