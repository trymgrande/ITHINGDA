/**
 * EnumEksempel.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Et eksempel p� en enum-type med egen konstrukt�r og egne medlemmer.
 */

import static javax.swing.JOptionPane.*;

enum Maaned {
  JANUAR(31), FEBRUAR(28), MARS(31), APRIL(30), MAI(31),
  JUNI(30), JULI(31), AUGUST(31), SEPTEMBER(30),
  OKTOBER(31), NOVEMBER(30), DESEMBER(31);

  private int antDg;

  private Maaned(int antDg) {
    this.antDg = antDg;
  }

  public int getAntDg() {
    return antDg;
  }
}

class EnumEksempel {
  public static void main(String[] args) {

    /* Leser inn et m�nedsnavn og finner antall dager i m�neden. */
    String mndLest = showInputDialog("M�nedsnavn: ");
    mndLest = mndLest.trim().toUpperCase();
    Maaned mnd = Maaned.valueOf(mndLest);
    int antDg = mnd.getAntDg();
    showMessageDialog(null, "M�neden har " + antDg + " dager.");

    Maaned[] alleMnd = Maaned.values(); // en tabell med alle m�nedene

    /* Leser inn et m�nedsnummer og finner antall dager i m�neden. */
    mndLest = showInputDialog("M�nedsnr (1-12): ");
    int nr = Integer.parseInt(mndLest) - 1;
    if (nr >= 0 && nr < alleMnd.length) { // gyldig verdi
      mnd = alleMnd[nr];
      antDg = mnd.getAntDg();
      String melding = "M�neden er " + mnd + ", og den har " + antDg + " dager.";
      showMessageDialog(null, melding);
    } else {
      showMessageDialog(null, "Ugyldig m�nedsnummer.");
    }
  }
}

/* Eksempeldata:
M�nedsnavn: mai
M�neden har 31 dager.
M�nednr. (1-12): 3
M�neden er MARS, og den har 31 dager.
*/
