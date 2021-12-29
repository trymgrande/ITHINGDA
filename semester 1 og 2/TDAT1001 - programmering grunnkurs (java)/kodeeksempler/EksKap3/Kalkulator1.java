/**
 * Kalkulator1.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn to tall og regner ut differansen mellom tallene.
 */

import static javax.swing.JOptionPane.*;
class Kalkulator1 {
  public static void main(String[] args) {
    String tall1Lest = showInputDialog("Tall 1: ");
    String tall2Lest = showInputDialog("Tall 2: ");
    int tall1 = Integer.parseInt(tall1Lest);
    int tall2 = Integer.parseInt(tall2Lest);
    int svar = tall1 - tall2;
    showMessageDialog(null, "Svar: " + svar);
  }
}

/* Eksempel på kjøring:
Tall1: 5
Tall2: 8
Svar: -3
*/