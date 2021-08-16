/**
 * ToDimLinjerMedUlikLengde.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen demonstrerer en todimensjonal tabell der linjene har forskjellig lengde.
 *
 */
class ToDimLinjerMedUlikLengde {
  public static void main(String[] args) {
    /* En tabell med tre linjer, der linjene kan ha forskjellige lengde. */
    int[][] tab = new int[3][];   // andre indeksgrense oppgis ikke

    /* Oppretter hver enkelt av de små tabellene (linjene) */
    tab[0] = new int[5];
    tab[1] = new int[4];
    tab[2] = new int[2];

    /*
     * Gir testverdier til tabellen, linje for linje.
     * tab[i].length gir lengden til linje nr. i.
     */
    for (int i = 0; i < tab[0].length; i++) {
      tab[0][i] = i;
    }
    for (int i = 0; i < tab[1].length; i++) {
      tab[1][i] = i;
    }
    for (int i = 0; i < tab[2].length; i++) {
      tab[2][i] = i;
    }

    /* Skriver ut hver enkelt linje. */
    for (int i = 0; i < tab.length; i++) {  // tab.length gir antall linjer
      System.out.print("Linjelengde: " + tab[i].length + " Innhold: ");
      for (int j = 0; j < tab[i].length; j++) {
        System.out.print(tab[i][j] + " ");
      }
      System.out.println();
    }
  }
}

/* Kjøring av programmet:

Linjelengde: 5 Innhold: 0 1 2 3 4
Linjelengde: 4 Innhold: 0 1 2 3
Linjelengde: 2 Innhold: 0 1
*/