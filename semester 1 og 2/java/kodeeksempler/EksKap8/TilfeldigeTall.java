/**
 * TilfeldigeTall.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet genererer og skriver ut fire pseudotilfeldige tall.
 */
import java.util.Random;
class TilfeldigeTall {
  public static void main(String[] args) {
    final int GRENSE = 100; // ønsker tall i intervallet [0, 99]
    final int FRØ = 17; // frøet bør være et primtall

    /* Vi lager et objekt av klassen */
    Random randomGen = new Random(FRØ);

    /* Vi ber om fire tilfeldige tall ved å sende meldinger til objektet */
    int tall1 = randomGen.nextInt(GRENSE);
    int tall2 = randomGen.nextInt(GRENSE);
    int tall3 = randomGen.nextInt(GRENSE);
    int tall4 = randomGen.nextInt(GRENSE);

    /* Vi skriver ut tallene */
    System.out.println(
      "Her er fire tilfeldige tall i intervallet [0, " +
      (GRENSE - 1) + "]: " + tall1 + " " + tall2 + " " + tall3 + " " + tall4);
  }
}

/* Kjøring av programmet:
Her er fire tilfeldige tall i intervallet [0, 99]: 76 20 94 16
*/