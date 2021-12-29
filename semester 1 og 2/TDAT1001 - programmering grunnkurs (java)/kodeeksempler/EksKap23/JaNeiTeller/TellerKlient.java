/**
 * TellerKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet henvender seg til rmi-registeret p� en tjenermaskin,
 * henter et stubbobjekt,
 * og sender meldinger til objektet p� tjenermaskinen via dette stubbobjektet.
 */

import java.rmi.Naming;
class TellerKlient {
  public static void main(String[] args) throws Exception {
    String url = "rmi://localhost/AS_Tellebyr�"; // localhost kan byttes ut
    /*
     * Finner objektet og f�r overf�rt et stubbobjekt som vi sender meldingene via.
     * Merk at klienten forholder seg til interfacet JaNeiteller,
     * og ikke til klassen JaNeitellerImpl.
     */
    JaNeiTeller tellemaskin = (JaNeiTeller) Naming.lookup(url);

    /* Sender meldinger til objektet p� tjenermaskinen */
    tellemaskin.�kAntallJa();
    tellemaskin.�kAntallNei();
    System.out.println("Antall ja: " + tellemaskin.getAntallJa() +
                       " Antall nei: " + tellemaskin.getAntallNei());
    tellemaskin.�kAntallJa(10);
    tellemaskin.�kAntallNei(20);
    System.out.println("Antall ja: " + tellemaskin.getAntallJa() +
                       " Antall nei: " + tellemaskin.getAntallNei());
  }
}

/* Kj�ring av programmet:
Antall ja: 1 Antall nei: 1
Antall ja: 11 Antall nei: 21
*/

