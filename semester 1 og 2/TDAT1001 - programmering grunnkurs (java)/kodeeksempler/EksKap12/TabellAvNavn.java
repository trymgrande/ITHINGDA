/**
 * TabellAvNavn.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Et lite program som oppretter en tabell med plass til ti strengobjekter.
 * Programmet går i løkke og leser inn navn som legges i denne tabellen.
 * Løkken stopper når tabellen er full eller brukeren har trykket på Esc-tasten.
 */

import static javax.swing.JOptionPane.*;
class TabellAvNavn {
  public static void main(String[] args) {
    String[] navnene = new String[10];
    int antNavn = 0;
    String navn = showInputDialog("Oppgi navn: ");
    while (antNavn < navnene.length && navn != null) {
      navnene[antNavn] = navn;
      antNavn++;
      navn = showInputDialog("Oppgi navn: ");
    }
    if (antNavn == navnene.length && navn != null) {
      showMessageDialog(null, "Ikke plass til flere navn.");
    }
    String liste = "Her er navnene:\n";
    for (int i = 0; i < antNavn; i++) {
      liste += navnene[i] + "\n";
    }
    showMessageDialog(null, liste);
  }
}