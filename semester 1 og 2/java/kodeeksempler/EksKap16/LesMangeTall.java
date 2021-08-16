/**
 * LesMangeTall.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Leser inn mange tall over flere linjer, og summerer dem
 */

import java.util.*;
import java.io.*;
class LesMangeTall {
  public static void main(String[] args) {
    String filnavn = "tallfil.txt";
    try {
      FileReader forbindelseTilFil = new FileReader(filnavn);
      BufferedReader leser = new BufferedReader(forbindelseTilFil);
      Scanner scan = new Scanner(leser);
      double sum = 0;
      try {
        while(scan.hasNext()) {
          double tall = scan.nextDouble(); // kan kaste InputMismatchException
          sum += tall;
        }
        System.out.println("Summen av tallene er " + sum + ".");
      } catch (InputMismatchException e) {
        System.out.println("Feil ved omforming til tall.");
      }
      scan.close();
      leser.close();

    } catch (FileNotFoundException e) {
      System.out.println("Fil ikke funnet: " + filnavn);
    } catch (IOException e) {
      System.out.println("IO-Feil ved åpning/lukking av fil: " + filnavn);
    }
  }
}

/*
Datafil:
  23,5 45 678 1 -56
-42 898 7,3
3 56 -90  0   67
 4,2

Utskrift:
Summen av tallene er 1595.0.
*/

