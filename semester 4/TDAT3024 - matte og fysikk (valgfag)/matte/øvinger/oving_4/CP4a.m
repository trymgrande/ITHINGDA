function [lam,u] = CP4a(A,x,k)
    Q = eye(size(A));
    for i=1:k
        u=x/norm(x);
        xtmp=A*u;
        lam=u'*xtmp;
        As=A-lam*Q;
        x=As\u;
    end
u=x/norm(x);
lam=u'*A*u;