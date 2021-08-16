% 0.1 CP1


% estimate p(1.00001) = 1+x+...+x.^50



% b = ones;
% 
% %x = 1:10;
% % q = ((x.^51)-1)/(x-1);
% 
% nest(50, 1, 1.00001, b);
% 
% %Program 0.1 Nested multiplication
% %Evaluates polynomial from nested form using Horner's method
% %Input: degree d of polynomial,
% %       array of d+1 coefficients (constant term first),
% %       x-coordinate x at which to evaluate, and
% %       array of d base points b, if needed
% %Output: value y of polynomial at x
% function y = nest(d,c,x,b)
%     if nargin < 4, b=zeros(d,1); end 
%     y = c(d+1);
%     for i=d:-1:1
%       y = y.*(x-b(i))+c(i);
%     end
% end

% 0.2
%3b





%7f

