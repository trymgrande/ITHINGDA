/**
 * SumAvTall.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet beregner og skriver ut alle summer fra 1 og opp til 10.
 */

class SumAvTall {
  public static void main(String[] args) {
    for (int linjeteller = 0; linjeteller < 10; linjeteller++) {
      int sum = 0;
      for (int tall = 1; tall <= linjeteller; tall++) {
        sum += tall;
        System.out.print(tall + " ");
      }
      System.out.println("Summen blir: " + sum);
    }
  }
}

/* Utskrift:
Summen blir: 0
1 Summen blir: 1
1 2 Summen blir: 3
1 2 3 Summen blir: 6
1 2 3 4 Summen blir: 10
1 2 3 4 5 Summen blir: 15
1 2 3 4 5 6 Summen blir: 21
1 2 3 4 5 6 7 Summen blir: 28
1 2 3 4 5 6 7 8 Summen blir: 36
1 2 3 4 5 6 7 8 9 Summen blir: 45
*/