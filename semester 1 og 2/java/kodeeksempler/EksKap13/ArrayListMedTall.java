/**
 * ArrayListMedTall.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Et lite program som oppretter et objekt av klassen ArrayList
 * og fyller det med tall.
 * Programmet går i løkke og leser inn tall som legges i listen.
 * Løkken stopper når brukeren trykker Escape-tasten.
 */

import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

class ArrayListMedTall {
  public static void main(String[] args) {
    ArrayList<Double> tallene = new ArrayList<Double>(); // OBS! <Double>

    /* Leser inn og lagrer tallene i ArrayList-objektet. */
    String tallLest = showInputDialog("Oppgi et tall: ");
    while (tallLest != null) {
      double tall = Double.parseDouble(tallLest);
      tallene.add(tall); // egentlig: tallene.add(new Double(tall));
      tallLest = showInputDialog("Oppgi et tall: ");
    }

    /* Viser alle tallene i ArrayList-objektet. Summerer dem. */
    System.out.print("Finner følgende tall: ");
    double sum = 0;
    for (double etTall : tallene) {
      sum += etTall;
      System.out.print(etTall + " ");
    }
    System.out.println("\nSummen av tallene er " + sum + ".");
  }
}

/* Eksempel på kjøring (leser inn tre tall: 2.7 4.5 1.0):
Finner følgende tall: 2.7 4.5 1.0
Summen av tallene er 8.2.
*/