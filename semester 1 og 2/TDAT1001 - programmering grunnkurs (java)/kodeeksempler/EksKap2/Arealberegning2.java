/**
 * Arealberegning2.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Leser inn lengde og bredde av et rektangel, deretter beregnes arealet.
 */

import static javax.swing.JOptionPane.*;
class Arealberegning2 {
  public static void main(String[] args) {
    String lengdeLest = showInputDialog("Lengde (meter): ");
    String breddeLest = showInputDialog("Bredde (meter): ");
    double lengde = Double.parseDouble(lengdeLest);
    double bredde = Double.parseDouble(breddeLest);
    double arealet = lengde * bredde;
    String utskrift = "Arealet av rektangelet er " +  arealet + " kvadratmeter";
    showMessageDialog(null, utskrift);
  }
}

/* Eksempeldata:
Lengde: 5.8 m
Bredde: 2.4 m
Arealet av rektangelet er 13.92 kvadratmeter.
*/