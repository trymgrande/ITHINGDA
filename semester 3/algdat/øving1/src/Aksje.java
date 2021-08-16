/*
resultater
n           tid(s)
10          0.000005
100         0.00015
1000        0.005
10 000      0.05
100 000     5
1 000 000   600
 */


import java.util.Random;
public class Aksje {


    public static void main(String[] args) {
        int n = 10;
        //int[] kursforandringPerDag = {-1, 3, -9, 2, 2, -1, 2, -1, -5};
        //genererer random int array
        int[] kursforandringPerDag = new int[n];
        Random rd = new Random();
        for (int i = 0; i < kursforandringPerDag.length; i++) {
            kursforandringPerDag[i] = rd.nextInt(5) - rd.nextInt(5);
            //System.out.println(kursforandringPerDag[i]);
        }
        //System.out.println();
        finnBesteAvkastning(kursforandringPerDag);

    }
    public static void finnBesteAvkastning(int[] kursforandringPerDag) {

        int[] besteAvkastnig = new int[3]; //avkastning, kjøpDag, selgDag
        int[] kursPerDag = new int[kursforandringPerDag.length];
        kursPerDag[0] = kursforandringPerDag[0];
           //oversetter tabell
        for (int i = 1; i < kursforandringPerDag.length; i++) {
            kursPerDag[i] = kursPerDag[i-1] + kursforandringPerDag[i];
        }

        long start = System.nanoTime();
        for (int i = 0; i < kursPerDag.length-1; i++) {     //2n+1
                    for (int j = i+1; j < kursPerDag.length;j++) {      //2n^2+n+1
                        int differanse = kursPerDag[j]-kursPerDag[i];   //4n^2
                        if (differanse > besteAvkastnig[0]) {           //2n^2
                            besteAvkastnig[0] = differanse;             //2n^2
                            besteAvkastnig[1] = i+1;                    //3n^2
                            besteAvkastnig[2] = j+1;                    //3n^2
                }
            }
        }
        double time = System.nanoTime() - start;

        //O(n)=n^2

        System.out.println(time / 1000000000 + " sek");
        System.out.println("avkastning: " + besteAvkastnig[0] + ", kjøp dag: " + besteAvkastnig[1] + ", selg dag: " + besteAvkastnig[2]);
    }
}