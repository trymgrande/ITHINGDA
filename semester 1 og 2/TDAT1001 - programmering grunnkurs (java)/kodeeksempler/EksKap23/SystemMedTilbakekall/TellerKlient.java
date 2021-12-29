/**
 * TellerKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er hovedprogrammet på klientsiden.
 * Kontakt med tjeneren opprettes og tilbakekallsobjekt (klientobjekt) lages.
 */

import static javax.swing.JOptionPane.*;
import java.rmi.Naming;
class TellerKlient {
  public static void main(String[] args) throws Exception {
    String objektnavn = "Åses_tellebyrå_AS";
    String maskinnavn = showInputDialog(null, "Navn på tjenermaskin:");
    String url = "rmi://" + maskinnavn + "/" + objektnavn;

    /* Henter et stubbobjekt som vi kan bruke til å kommunisere med tjeneren */
    TellemaskinFront tellemaskin = (TellemaskinFront) Naming.lookup(url);

    String klientnavn = showInputDialog(null, "Oppgi klientnavn:");
    System.out.println("Her er kommandovinduet til " + klientnavn);

    /* Vi får vinduet på plass. */
    TelleVindu vindu = new TelleVindu(tellemaskin, klientnavn);
    vindu.setVisible(true);

    /* Lager objekt som kan motta meldinger fra tjenersiden */
    Klient denneKlienten = new KlientImpl(klientnavn, vindu);

    /* Vi registrerer oss hos tjeneren. */
    tellemaskin.registrerMeg(denneKlienten);
  }
}