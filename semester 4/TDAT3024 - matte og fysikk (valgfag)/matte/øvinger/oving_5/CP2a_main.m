x=[0 1 2 3]'; y=[3 5 4 1]';
coeffs=CP2a(x,y,4,5)

colors=[[1 0 0];[0 0 1];[0 1 0]]; ...


close all;hold on; 
for i=1:3 ; ...
    plot(i-1:.1:i,nest(3,coeffs(i,:),i-1:.1:i,(i-1)*ones(3)),'color',colors(i,:)); ...
end