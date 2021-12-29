/**
 * Arealberegning3.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet beregner arealet av et rektangel.
 */

import static javax.swing.JOptionPane.*;
class Arealberegning3 {
  public static void main(String[] args) {
    String lengdeLest = showInputDialog("Lengde (meter): ");
    String breddeLest = showInputDialog("Bredde (meter): ");
    double lengde = Double.parseDouble(lengdeLest);
    double bredde = Double.parseDouble(breddeLest);

    /* Beregner areal bare dersom det har mening */
    if (lengde > 0.0 && bredde > 0.0) {
      double arealet = lengde * bredde;
      showMessageDialog(null, "Arealet er " + arealet + " kvadratmeter");
    } else { // …ellers…
      showMessageDialog(null, "Lengde og/eller bredde har ugyldig verdi.");
    }
  }
}

/*
Kjøring 1:
Lengde: 5 m
Bredde: 2 m
Arealet er 10.0 kvadratmeter.

Kjøring 2:
Lengde: -3 m
Bredde: 2 m
Lengde og/eller bredde har ugyldig verdi.
*/
