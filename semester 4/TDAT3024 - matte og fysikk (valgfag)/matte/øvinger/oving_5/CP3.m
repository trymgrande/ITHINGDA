format longg
Yc=[1960,1970,1990,2000];
Pc=[3039585530,3707475887,5281653820,6079603571];

Ya=Yc(2:3);
Pa=Pc(2:3);

Yb=Yc(1:3);
Pb=Pc(1:3);

ca=newtdd(Ya,Pa,2);
cb=newtdd(Yb,Pb,3);
cc=newtdd(Yc,Pc,4);

nest(1,ca,1980,Ya)
nest(2,cb,1980,Yb)
nest(3,cc,1980,Yc)