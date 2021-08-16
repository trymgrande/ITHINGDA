function CP4a_main()
    A=[10,-12,-6;5,-5,-4;-1,0,3];
    u0=[1,1,1]';
    [lam1,u1]=CP4a(A,u0,10);
    [lam,u0]=CP2a(A,u0,2.9,1);
    [lam2,u2]=CP4a(A,u0,5);
    u0=[1,0,0]';
    [lam3,u3]=CP4a(A,u0,5);
    [ u1 u2 u3 ]
    [ lam2 lam2 lam3 ]