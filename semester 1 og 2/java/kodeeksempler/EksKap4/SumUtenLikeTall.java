/**
 * SumUtenLikeTall.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn heltall fra brukeren og regner ut summen.
 * Hvis flere like tall f�lger etter hverandre, tas de med bare �n gang
 * i summen. Brukeren avslutter tallrekken med Esc.
 */

import static javax.swing.JOptionPane.*;
class SumUtenLikeTall {
  public static void main(String[] args) {
    int antallTallSummert = 0;
    int sum = 0;
    String forrigeTallLest = "";
    String tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");

    /* G� i l�kke s� lenge som Esc ikke er tastet */
    while (tallLest != null) {
      if (!tallLest.equals(forrigeTallLest)) {
        int tall = Integer.parseInt(tallLest);
        sum += tall;
        antallTallSummert++;
      }
      forrigeTallLest = tallLest; // tar vare p� siste innleste tall
      tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");
    }
    showMessageDialog(null, "Summen er " + sum
                       + ". Vi har summert " + antallTallSummert + " tall.");
  }
}

/* Eksempler p� kj�ring:
Kj�ring 1:
Skriv tall, avslutt med Esc.
3 3 3 3
Summen er 3. Vi har summert 1 tall.

Kj�ring 2:
Skriv tall, avslutt med Esc.
1 7 3 3 3 2
Summen er 13. Vi har summert 4 tall.
*/