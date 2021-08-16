/**
 * BinaerSoek.java  - "Programmering i Java", 4.utgave - 2009-07-01
 */

class Binaersoek {

  /**
   * Bin�rs�k. Forutsetter at tabellen er sortert.
   * Metoden returnerer indeksen der verdien finnes.
   * Hvis verdien ikke finnes, returneres et negativt tall som er slik at
   * verdien kan settes inn p� indeks (- tall -1) slik at
   * tabellen fortsatt er sortert.
   */
  public static int bin�rs�kHeltallstabell(int[] tabell, int verdi) {
    int start = 0;
    int slutt = tabell.length - 1;
    while (start <= slutt) {
      int midten = (start + slutt) / 2;
      if (tabell[midten] == verdi) {
        return midten;  // RETUR. Verdien er funnet.
      } else {
        if (tabell[midten] < verdi) {
          start = midten + 1;
        } else {
          slutt = midten - 1;
        }
      }
    }
    return -start - 1;  // verdien ikke funnet.
  }

  /* Testklient */
  public static void main(String[] args) {
    int[] enTabell = {-5, -5, -2, 0, 3, 4, 8, 11, 11, 13, 17, 22, 22};
   System.out.println("Totalt antall tester: 6");
    int indeks = bin�rs�kHeltallstabell(enTabell, 22);  // tall finnes
    if (indeks == 11 || indeks == 12) {
      System.out.println("Binaersoek: Test 1 vellykket");
    }
    indeks = bin�rs�kHeltallstabell(enTabell, 0); // tall finnes
    if (indeks == 3) {
      System.out.println("Binaersoek: Test 2 vellykket");
    }
    indeks = bin�rs�kHeltallstabell(enTabell, -5); // tall finnes
    if (indeks == 0 || indeks == 1) {
      System.out.println("Binaersoek: Test 3 vellykket");
    }
    indeks = bin�rs�kHeltallstabell(enTabell, -8); // tall finnes ikke
    if ((-indeks - 1) == 0) {
      System.out.println("Binaersoek: Test 4 vellykket");
    }
    indeks = bin�rs�kHeltallstabell(enTabell, 5); // tall finnes ikke
    if ((-indeks - 1) == 6) {
      System.out.println("Binaersoek: Test 5 vellykket");
    }
    indeks = bin�rs�kHeltallstabell(enTabell, 25); // tall finnes ikke
    if ((-indeks - 1) == 13) {
      System.out.println("Binaersoek: Test 6 vellykket");
    }
  }
}
