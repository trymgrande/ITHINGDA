/**
 * Karakter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn en poengsum og beregner en karakter ut
 * fra en beslutningstabell.
 */

import static javax.swing.JOptionPane.*;
class Karakter {
  public static void main(String[] args) {
    final int MAKS = 100;
    String poengLest = showInputDialog("Antall poeng: ");
    int poeng = Integer.parseInt(poengLest);

    String melding;
    if (poeng > 100) {
      melding = "For stor poengsum, maks. " + MAKS + ".";
    } else if (poeng >= 96) {
      melding = "Karakteren er A.";
    } else if (poeng >= 86) {
      melding = "Karakteren er B.";
    } else if (poeng >= 71) {
      melding = "Karakteren er C.";
    } else if (poeng >= 55) {
      melding = "Karakteren er D.";
    } else if (poeng >= 36) {
      melding = "Karakteren er E.";
    } else if (poeng >= 0) {
      melding = "Karakteren er F.";
   } else {
     melding = "Poengsummen kan ikke være negativ.";
   }
   showMessageDialog(null, melding);
 }
}

/*
Kjøring 1:
Antall poeng: 67
Karakteren er D.

Kjøring 2:
Antall poeng: 200
For stor poengsum, maks. 100.

Kjøring 3:
Antall poeng: -5
Poengsummen kan ikke være negativ.
*/
