/**
 * Tekstanalyse.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet analyserer en tekst ved å finne antall ord og
 * gjennomsnittlig ordlengde. Teksten og skilletegnene leses inn fra brukeren.
 */

import java.util.StringTokenizer;
import static javax.swing.JOptionPane.*;
class Tekstanalyse {
  public static void main(String[] args) {
    String tekst = showInputDialog("Teksten: ");
    String skilletegn = showInputDialog("Skilletegn: ");
    StringTokenizer analyse = new StringTokenizer(tekst, skilletegn);
    int antOrd = analyse.countTokens();
    int antBokst = 0;
    while (analyse.hasMoreTokens()) {
      String ord = analyse.nextToken();
      antBokst += ord.length();
      System.out.println(ord);
    }
    if (antOrd > 0) {
      double gjsnitt = (double) antBokst / (double) antOrd;
      showMessageDialog(null, "Antall ord er " + antOrd
                                                  + ".\nGjennomsnittlig ordlengde er " + gjsnitt);
   } else {
      showMessageDialog(null, "Ingen ord skrevet inn.");
    }
  }
 }

/* Eksempel på kjøring:
Inndata:
Tekst:  Anne-Berit    Hansen,  Storgt. 34,  , 2356 Heia.
Skilletegn: ,.-

Utskrift:
Anne
Berit
Hansen
Storgt
34
2356
Heia

Utskrift i meldingsboksen:
Antall ord er 7.
Gjennomsnittling ordlengde er 4.428571428571429
*/