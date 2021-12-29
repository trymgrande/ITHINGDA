import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //2.1-1
        System.out.println(power(5, 9));
        //2.2-3
        System.out.println(powerv2(5, 9));
        //3
        System.out.println(powerMath(5, 9));

        System.out.println();

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
           power(1.001,5000);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);
    }

    public static double power(double x, int n) {   //0
        if (n == 0) {   //2(n-1)
            return 1;   //1
        }
        return x*power(x, n-1); //4n
    }

    public static double powerv2(double x, int n) {     //0
        if (n == 0) {   //2n
            return 1;   //1
        }
        //oddetall
        else if ((n % 2) == 1) {  //3n
            return x*powerv2(x*x, (n-1)/2); //5n
        }
        //partall
        return powerv2(x*x, n/2);   //3n
    }

    public static double powerMath(double x, int n) {   //0
        return Math.pow(x, n);  //x+1
    }
}