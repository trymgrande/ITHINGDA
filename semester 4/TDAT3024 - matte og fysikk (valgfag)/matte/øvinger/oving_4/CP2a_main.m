function CP2a_main()
    A=[10,-12,-6;5,-5,-4;-1,0,3];
    u0=[1,1,1]';
    [lam,u]=CP2a(A,u0,0,100);
    disp(lam);
end