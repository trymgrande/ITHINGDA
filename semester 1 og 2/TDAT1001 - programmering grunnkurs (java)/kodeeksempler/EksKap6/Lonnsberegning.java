/**
 *
 * Lonnsberegning.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 *  Regner ut bruttolønn og skattetrekk for flere arbeidsøkter.
 */

import static javax.swing.JOptionPane.*;
class Lonnsberegning {
  public static void main(String[] args) {
    Ansatt enAnsatt = new Ansatt(12345, "Anne Vik", 400);
    String antTimerLest = showInputDialog("Antall timer, avslutt med Esc: ");
    while (antTimerLest != null) {
      double antTimer = Double.parseDouble(antTimerLest);
      double bruttolønn = enAnsatt.beregnBruttolønn(antTimer);
      String utskrift = "Antall timer: " + antTimer + " gir bruttolønn " + bruttolønn;
      double skatt = enAnsatt.beregnSkattetrekk(antTimer);
      utskrift += (" og skattetrekk " + skatt);
      showMessageDialog(null, utskrift);
      antTimerLest = showInputDialog("Antall timer, avslutt med Esc: ");
    }
  }
}

/* Kjøring av programmet:
Antall timer, avslutt med Esc: 10
Antall timer: 10.0 gir bruttolønn 4000.0 og skattetrekk 2400.0
Antall timer, avslutt med Esc: 20
Antall timer: 20.0 gir bruttolønn 8000.0 og skattetrekk 4800.0
*/