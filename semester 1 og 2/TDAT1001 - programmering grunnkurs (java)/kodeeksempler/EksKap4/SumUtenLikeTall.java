/**
 * SumUtenLikeTall.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn heltall fra brukeren og regner ut summen.
 * Hvis flere like tall følger etter hverandre, tas de med bare én gang
 * i summen. Brukeren avslutter tallrekken med Esc.
 */

import static javax.swing.JOptionPane.*;
class SumUtenLikeTall {
  public static void main(String[] args) {
    int antallTallSummert = 0;
    int sum = 0;
    String forrigeTallLest = "";
    String tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");

    /* Gå i løkke så lenge som Esc ikke er tastet */
    while (tallLest != null) {
      if (!tallLest.equals(forrigeTallLest)) {
        int tall = Integer.parseInt(tallLest);
        sum += tall;
        antallTallSummert++;
      }
      forrigeTallLest = tallLest; // tar vare på siste innleste tall
      tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");
    }
    showMessageDialog(null, "Summen er " + sum
                       + ". Vi har summert " + antallTallSummert + " tall.");
  }
}

/* Eksempler på kjøring:
Kjøring 1:
Skriv tall, avslutt med Esc.
3 3 3 3
Summen er 3. Vi har summert 1 tall.

Kjøring 2:
Skriv tall, avslutt med Esc.
1 7 3 3 3 2
Summen er 13. Vi har summert 4 tall.
*/