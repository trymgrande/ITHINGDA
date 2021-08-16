package Algdat.Øving4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CharStack
{
    public static void main(String[] args)
    {
        Stack charStack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            do
            {
                int c = br.read();
                if (c == '[' || c == '{' || c == '(')
                {
                    charStack.push(c);
                }
                else if (c == ')' || c == '}' || c == ']')
                {
                    int i = Integer.parseInt(charStack.pop().toString());
                    if ((i == '(' && c != ')') || (i == '{' && c != '}') || (i == '[' && c != ']'))
                    {
                        System.out.println("nesting is wrong");
                        break;
                    }
                }
            } while (br.ready());
        }
        catch (Exception e)
        {
            System.out.println("nesting is wrong");
            e.printStackTrace();
        }
    }
}
