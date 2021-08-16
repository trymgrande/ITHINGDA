/**
 * SorteringAvTall.java  - "Programmering i Java", 4.utgave - 2012-06-01
 *
 * Filen inneholder to klasser:
 * Klassen Sortering med en sorteringsmetode (klassemetode) og klassen
 * SorteringAvTall, som er en liten klient som viser bruk av sorteringsmetoden.
 */

class Sortering {
  public static void sorterHeltallstabell(int[] tabell) {
    for (int start = 0; start < tabell.length; start++) {
      int hittilMinst = start;
      for (int i = start + 1; i < tabell.length; i++) {
        if (tabell[i] < tabell[hittilMinst]) {
          hittilMinst = i;
        }
      }
      int hjelp = tabell[hittilMinst];
      tabell[hittilMinst] = tabell[start];
      tabell[start] = hjelp;
    }
  }
}

class SorteringAvTall {
  public static void main(String[] args) {
    int[] enTabell = {-5, 4, -5, 13, 11, 0, 8, -2, 22, 3, 11, 22, 17};

    Sortering.sorterHeltallstabell(enTabell);

    /* Utskrift av resultatet */
    System.out.print("Sorterte tall: ");
    for (int i = 0; i < enTabell.length; i++) {
      System.out.print(enTabell[i] + " ");
    }
    System.out.println();
  }
}


/* Utskrift:
Sorterte tall: -5 -5 -2 0 3 4 8 11 11 13 17 22 22
*/
