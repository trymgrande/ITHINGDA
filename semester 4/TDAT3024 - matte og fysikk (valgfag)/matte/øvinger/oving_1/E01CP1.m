% 0.1 CP1


% estimate p(1.00001) = 1+x+...+x.^50

q = ((x.^51)-1)/(x-1);

%Program 0.1 Nested multiplication
%Evaluates polynomial from nested form using Horner's method
%Input: degree d of polynomial,
%       array of d+1 coefficients (constant term first),
%       x-coordinate x at which to evaluate, and
%       array of d base points b, if needed
%Output: value y of polynomial at x
c = ones(51,1);
x = 1.00001;
y = nest(50, c, x); 
       
disp('y: ');
disp(y);    

error = y - q;
disp('error:')
disp(error);
