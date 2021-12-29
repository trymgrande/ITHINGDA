/**
 * SumMedGrense.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn heltall fra brukeren og regner ut summen.
 * Brukeren avslutter tallrekken med Esc.
 * Innlesingen stopper tidligere dersom summen passerer 10.
 */

import static javax.swing.JOptionPane.*;
class SumMedGrense {
  public static void main(String[] args) {
    final int GRENSE = 10;
    boolean grenseNådd = false;
    int sum = 0;
    String tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");

    /* Gå i løkke så lenge som Esc ikke er tastet og grense ikke nådd */
    while (tallLest != null && !grenseNådd) {
      int tall = Integer.parseInt(tallLest);
      sum += tall;
      if (sum > GRENSE) {
        grenseNådd = true;
      } else {  // skal lese inn nytt tall bare dersom grensen ikke er nådd
        tallLest = showInputDialog("Skriv ett tall av gangen, avslutt med Esc: ");
      }
    }

    /* Skriver ut resultatet */
    showMessageDialog(null, "Summen er " + sum + ".");
  }
}

/* Eksempel på kjøring:
Se tabell med testdata.
*/