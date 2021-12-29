/**
 * SkrivMangeLinjer.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet skriver fem linjer på skjermen.
 */
class SkrivMangeLinjer {
  public static void main(String[] args) {
    int teller = 0;
    while (teller < 5) {
      System.out.println("Dette er en linje");
      teller = teller +1;
    }
  }
}

/* Utskrift:
Dette er en linje
Dette er en linje
Dette er en linje
Dette er en linje
Dette er en linje
*/
