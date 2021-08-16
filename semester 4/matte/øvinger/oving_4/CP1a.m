function [lam,u] = CP1a( A , x , k)
    for i=1:k
        u=x/norm(x);
        x=A*u;
        lam=u'*x;
    end
u=x/norm(x);