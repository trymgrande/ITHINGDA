/**
 * ArrayListAvNavn.java   - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Et lite program som oppretter et objekt av klassen ArrayList
 * og fyller det med tekster.
 * Programmet går i løkke og leser inn navn som legges i listen.
 * Løkken stopper når brukeren trykker Escape-tasten.
 */

import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

class ArrayListAvNavn {
  public static void main(String[] args) {
    ArrayList<String> navnene = new ArrayList<String>();
    String navn = showInputDialog("Oppgi navn: ");
    while (navn != null) {
      navn = navn.trim();
      navnene.add(navn); // add() er en metode i klassen ArrayList
      navn = showInputDialog("Oppgi navn: ");
    }
    /* Henter ut og skriver ut alle navnene. */
    for (int i = 0; i < navnene.size(); i++) {
      String detteNavnet = navnene.get(i);
      System.out.println(detteNavnet);
    }
  }
}