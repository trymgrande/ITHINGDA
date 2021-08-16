public class Integralkalkulator {
    public static double f(double x) {
        double ret = Math.sqrt((Math.pow(x, 3) - 1));
        return ret;
    }

    public static double trapes(int n) {
//        int input = Math.sqrt(Ma
        //a

        double x;
        double A = 0;
        double Atot = 0;
        int b = 2; //upper
        int a = 1; //lower
        double dx = (double) (b - a) / n;
        for (int i = 0; i < n; i++) {
            double xi = a + dx * i;
            double xiplus1 = xi + dx;
            double f1 = f(xi);
            double f2 = f(xiplus1);
            //area
            if (f1 < f2) {
                A = (dx * f1 + ((dx * (f2 - f1)) / 2));
            } else if (f2 < f1) {
                A = (dx * f2 + ((dx * (f1 - f2)) / 2));
            }
            Atot += A;
        }
        return Atot;
    }

    public static void main(String[] args) {

        System.out.println("trapesregelen:");
        System.out.println("n=10");
        System.out.println(trapes(10));
        System.out.println("n=100");
        System.out.println(trapes(100));
        System.out.println("n=1000");
        System.out.println(trapes(1000));
    }
}