/**
 * Nedboer.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn data for 7 dager.
 * Deretter beregnes antall dager uten nedbør og totalnedbøren
 * for disse dagene. (Dette problemet kan også løses uten bruk
 * av tabell ved at telling og summering foregår i innlesingsløkken.)
 */

import static javax.swing.JOptionPane.*;
class Nedboer {
  public static void main(String[] args) {
    final int ANT_DG = 7;

    int[] dager = new int[ANT_DG];
    for (int i = 0; i < dager.length; i++) {
      String tallLest = showInputDialog("Nedbør på dag nr " + (i + 1) + ": ");
      dager[i] = Integer.parseInt(tallLest);
    }

    int antDgUtenNedbør = 0;
    for (int i = 0; i < dager.length; i++) {
      if (dager[i] == 0) {
        antDgUtenNedbør++;
      }
    }

    int total = 0;
    for (int i = 0; i < dager.length; i++) {
      total += dager[i];
    }

    showMessageDialog(null, "Antall dager uten nedbør: "
                           + antDgUtenNedbør + "\n" + "Total nedbør: " + total);
  }
}

/* Eksempeldata:
Nedbør: 3, 3, 3, 0, 0, 0, 4
Utskrift:
Antall dager uten nedbør: 3
Total nedbør: 13
*/