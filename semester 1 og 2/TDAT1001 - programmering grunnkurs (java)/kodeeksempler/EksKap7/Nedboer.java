/**
 * Nedboer.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn data for 7 dager.
 * Deretter beregnes antall dager uten nedb�r og totalnedb�ren
 * for disse dagene. (Dette problemet kan ogs� l�ses uten bruk
 * av tabell ved at telling og summering foreg�r i innlesingsl�kken.)
 */

import static javax.swing.JOptionPane.*;
class Nedboer {
  public static void main(String[] args) {
    final int ANT_DG = 7;

    int[] dager = new int[ANT_DG];
    for (int i = 0; i < dager.length; i++) {
      String tallLest = showInputDialog("Nedb�r p� dag nr " + (i + 1) + ": ");
      dager[i] = Integer.parseInt(tallLest);
    }

    int antDgUtenNedb�r = 0;
    for (int i = 0; i < dager.length; i++) {
      if (dager[i] == 0) {
        antDgUtenNedb�r++;
      }
    }

    int total = 0;
    for (int i = 0; i < dager.length; i++) {
      total += dager[i];
    }

    showMessageDialog(null, "Antall dager uten nedb�r: "
                           + antDgUtenNedb�r + "\n" + "Total nedb�r: " + total);
  }
}

/* Eksempeldata:
Nedb�r: 3, 3, 3, 0, 0, 0, 4
Utskrift:
Antall dager uten nedb�r: 3
Total nedb�r: 13
*/