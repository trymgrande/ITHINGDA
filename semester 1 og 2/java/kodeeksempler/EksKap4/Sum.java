/**
 * Sum.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn heltall fra brukeren og regner ut summen.
 * Brukeren avslutter tallrekken ved å trykke Esc-tasten.
 */

import static javax.swing.JOptionPane.*;
class Sum {
  public static void main(String[] args) {
    int sum = 0;
    String tallLest = showInputDialog("Skriv tall, avslutt med Esc.");
    while (tallLest != null) {
      int tall = Integer.parseInt(tallLest);
      sum += tall;
      tallLest = showInputDialog("Skriv tall, avslutt med Esc.");
    }
  showMessageDialog(null, "Summen er " + sum + ".");
  }
}

/* Eksempler på kjøring:
Kjøring 1:
Skriv tall, avslutt med Esc.
-4 7 2
Summen er 5.

Kjøring 2 (trykker Esc med en gang):
Skriv tall, avslutt med Esc.
Summen er 0.
*/