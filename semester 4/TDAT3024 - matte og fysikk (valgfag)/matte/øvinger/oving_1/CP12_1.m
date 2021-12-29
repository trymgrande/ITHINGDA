% a)
f = @(x)(2*x+2)^(1/3);
fixedPoint(f,1,0.5e-8);
disp('---------------------')

% b)
g = @(x)log(7-x)
fixedPoint(g,1,0.5e-8);
disp('---------------------')

% c)
h = @(x)log(4-sin(x));
fixedPoint(h,1,0.5e-8);


function result = fixedPoint(f, x, epsilon)
    i=0;
    while(true)
        result = f(x);
        disp(result);
        if (abs(result-x) < epsilon); break; end
        i = i+1;
        x = result;
    end
end