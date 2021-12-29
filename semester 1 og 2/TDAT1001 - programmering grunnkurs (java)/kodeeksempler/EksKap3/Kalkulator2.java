/**
 * Kalkulator2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn to tall og trekker det minste tallet fra det st�rste.
 */

import static javax.swing.JOptionPane.*;
class Kalkulator2 {
  public static void main(String[] args) {
    String tall1Lest = showInputDialog("Tall 1: ");
    String tall2Lest = showInputDialog("Tall 2: ");
    int tall1 = Integer.parseInt(tall1Lest);
    int tall2 = Integer.parseInt(tall2Lest);

    String utskrift = "";
    if (tall1 < tall2) {
      int svar = tall2 - tall1;
      utskrift = utskrift + "Det f�rste tallet er minst. Differansen er " + svar + ".";
    } else {
      int svar = tall1 - tall2;
      utskrift = utskrift + "Det f�rste tallet er st�rst, eller tallene er like. "
                                  + "Differansen er " + svar + ".";
    }
    showMessageDialog(null, utskrift);
  }
}

/*
Kj�ring 1:
Tall 1: 5
Tall 2: 7
Det f�rste tallet er minst. Differansen er 2.

Kj�ring 2:
Tall 1: -5
Tall 2: -8
Det f�rste tallet er st�rst, eller tallene er like. Differansen er 3.
*/