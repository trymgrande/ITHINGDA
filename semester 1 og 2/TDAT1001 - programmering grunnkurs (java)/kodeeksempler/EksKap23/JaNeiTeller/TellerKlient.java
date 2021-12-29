/**
 * TellerKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet henvender seg til rmi-registeret på en tjenermaskin,
 * henter et stubbobjekt,
 * og sender meldinger til objektet på tjenermaskinen via dette stubbobjektet.
 */

import java.rmi.Naming;
class TellerKlient {
  public static void main(String[] args) throws Exception {
    String url = "rmi://localhost/AS_Tellebyrå"; // localhost kan byttes ut
    /*
     * Finner objektet og får overført et stubbobjekt som vi sender meldingene via.
     * Merk at klienten forholder seg til interfacet JaNeiteller,
     * og ikke til klassen JaNeitellerImpl.
     */
    JaNeiTeller tellemaskin = (JaNeiTeller) Naming.lookup(url);

    /* Sender meldinger til objektet på tjenermaskinen */
    tellemaskin.økAntallJa();
    tellemaskin.økAntallNei();
    System.out.println("Antall ja: " + tellemaskin.getAntallJa() +
                       " Antall nei: " + tellemaskin.getAntallNei());
    tellemaskin.økAntallJa(10);
    tellemaskin.økAntallNei(20);
    System.out.println("Antall ja: " + tellemaskin.getAntallJa() +
                       " Antall nei: " + tellemaskin.getAntallNei());
  }
}

/* Kjøring av programmet:
Antall ja: 1 Antall nei: 1
Antall ja: 11 Antall nei: 21
*/

