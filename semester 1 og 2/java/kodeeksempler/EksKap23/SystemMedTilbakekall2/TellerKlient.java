/*
 * TellerKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er hovedprogrammet p� klientsiden.
 * Kontakt med tjeneren opprettes og tilbakekallsobjekt (klientobjekt) lages.
 */

import static javax.swing.JOptionPane.*;
import java.rmi.Naming;
class TellerKlient {
  public static void main(String[] args) throws Exception {
    String objektnavn = "�ses_tellebyr�_AS";
    String maskinnavn = showInputDialog(null, "Navn p� tjenermaskin:");
    String url = "rmi://" + maskinnavn + "/" + objektnavn;

    /* Henter et stubbobjekt som vi kan bruke til � kommunisere med tjeneren */
    TellemaskinFront tellemaskin = (TellemaskinFront) Naming.lookup(url);

    String klientnavn = showInputDialog(null, "Oppgi klientnavn:");
    System.out.println("Her er konsollvinduet til " + klientnavn);

    /* Lager objekt som kan motta meldinger fra tjenersiden */
    Klient denneKlienten = new KlientImpl(klientnavn);

    /* Vi f�r vinduet p� plass. */
    TelleVindu vindu = new TelleVindu(tellemaskin, denneKlienten);
    vindu.setVisible(true);

    /*
     * Statusmeldinger til klienten skal skrives ut i vinduet.
     * Klienten m� derfor vite om vinduet.
     */
    denneKlienten.setVindu(vindu);

    /* Vi registrerer oss hos tjeneren. */
    tellemaskin.registrerMeg(denneKlienten);
  }
}