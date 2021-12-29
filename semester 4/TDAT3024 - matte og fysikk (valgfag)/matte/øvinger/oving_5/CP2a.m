function coeffs = CP2a( x, y, n , mode)
%Beskrivelse: x - n-vektor med x-verdier
% : y - n-vektor med y-verdier
if nargin == 3; mode = '1'; end
dx=x(2:n)-x(1:n-1);
dy=y(2:n)-y(1:n-1);
A=sparse(n,n); % Tynn matrise
for i=1:n-2
A(i+1,i:i+2)=[dx(i),2*(dx(i)+dx(i+1)),dx(i+1)];
end
switch mode
case 5 % Not-a-knot spline
A(1,1:3)=[dx(2) -dx(1)-dx(2) dx(1)];
A(n,n-2:n)=[dx(n-1) -dx(n-2)-dx(n-1) dx(n-2)];
B(1)=0; B(n)=0;
case 1 % Normal slpine
A(1,1:2)=[1 0]; A(n,n-1:n)=[0 1];
B(1)=0; B(n)=0;
otherwise
end
DyDx=dy./dx;
B(2:n-1)=3*(DyDx(2:n-1)-DyDx(1:n-2));
c=A\B';
b=DyDx-dx/3.*(2*c(1:n-1)+c(2:n));
d=(c(2:n)-c(1:n-1))./(3*dx);
coeffs = [y(1:n-1) b c(1:n-1) d];
end

