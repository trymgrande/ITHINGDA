/**
 *
 * Lonnsberegning.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 *  Regner ut bruttol�nn og skattetrekk for flere arbeids�kter.
 */

import static javax.swing.JOptionPane.*;
class Lonnsberegning {
  public static void main(String[] args) {
    Ansatt enAnsatt = new Ansatt(12345, "Anne Vik", 400);
    String antTimerLest = showInputDialog("Antall timer, avslutt med Esc: ");
    while (antTimerLest != null) {
      double antTimer = Double.parseDouble(antTimerLest);
      double bruttol�nn = enAnsatt.beregnBruttol�nn(antTimer);
      String utskrift = "Antall timer: " + antTimer + " gir bruttol�nn " + bruttol�nn;
      double skatt = enAnsatt.beregnSkattetrekk(antTimer);
      utskrift += (" og skattetrekk " + skatt);
      showMessageDialog(null, utskrift);
      antTimerLest = showInputDialog("Antall timer, avslutt med Esc: ");
    }
  }
}

/* Kj�ring av programmet:
Antall timer, avslutt med Esc: 10
Antall timer: 10.0 gir bruttol�nn 4000.0 og skattetrekk 2400.0
Antall timer, avslutt med Esc: 20
Antall timer: 20.0 gir bruttol�nn 8000.0 og skattetrekk 4800.0
*/