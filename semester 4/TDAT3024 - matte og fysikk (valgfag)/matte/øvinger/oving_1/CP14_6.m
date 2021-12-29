function CP14_6( antallDesimaler )
    error = 0.5*10^(-antallDesimaler);
    r=1;
    i=0;
    fprintf('r[0] = 1\n');
    while(not(Df(r)==0))
        newr=newton(r);i=i+1;
        fprintf('r[%d] = %f\n', i, newr);
        if(abs(newr-r)< error); break; end
        r=newr;
    end
end

function y = f(r); y = (10*pi*r^2/3)+2*pi*r^3/3-60; end
function y = Df(r); y = (20*pi*r/3)+2*pi*r^2; end
function newr = newton(oldr); newr=oldr-f(oldr)/Df(oldr); end

