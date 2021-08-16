/**
 * DataLeser.java- "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En klasse med generelle metoder for innlesing av tekst og tall.
 * Alle metodene er klassemetoder.
 */
package mittBibliotek;
import static javax.swing.JOptionPane.*;
class DataLeser {

  /**
   * Leser en tekst fra brukeren. Blank tekst godtas ikke.
   * Teksten "trimmes" før den returneres til klienten.
   */
  public static String lesTekst(String ledetekst) {
    String tekst = showInputDialog(ledetekst);
    while (tekst == null || tekst.trim().equals("")) {
      showMessageDialog(null, "Du må oppgi data.");
      tekst = showInputDialog(ledetekst);
    }
    return tekst.trim();
  }

  /**
   * Leser et heltall fra brukeren. Desimaltall godtas ikke.
   */
  public static int lesHeltall(String ledetekst) {
    int tall = 0;
    boolean ok = false;
    String melding = ledetekst;
    do {
      String tallSomTekst = lesTekst(melding);
      try {
        tall = Integer.parseInt(tallSomTekst);
        ok = true;
      } catch (NumberFormatException e) {
        melding = "Du skrev: " + tallSomTekst
          + ".\nDet er et ugyldig heltall. Prøv på nytt.\n" + ledetekst;
      }
    } while (!ok);
    return tall;
  }

  /**
   * Leser et desimaltall fra brukeren. Heltall godtas også.
   */
  public static double lesDesimaltall(String ledetekst) {
    double tall = 0;
    boolean ok = false;
    String melding = ledetekst;
    do {
      String tallSomTekst = lesTekst(melding);
      try {
        tall = Double.parseDouble(tallSomTekst);
        ok = true;
      } catch (NumberFormatException e) {
        melding = "Du skrev: " + tallSomTekst
          + ".\nDet er et ugyldig desimaltall. Prøv på nytt.\n" + ledetekst;
      }
    } while (!ok);
    return tall;
  }

  /**
   * Meget enkel testklient
   */
  public static void main(String[] args) {
    String tekst = DataLeser.lesTekst("Skriv en tekst: ");
    double desimaltall = DataLeser.lesDesimaltall("Skriv et desimaltall: ");
    int heltall = DataLeser.lesHeltall("Skriv et heltall: ");
    String melding = "Du skrev dette:\n" + tekst + "\n" + desimaltall + "\n" + heltall;
    showMessageDialog(null, melding);
  }
}