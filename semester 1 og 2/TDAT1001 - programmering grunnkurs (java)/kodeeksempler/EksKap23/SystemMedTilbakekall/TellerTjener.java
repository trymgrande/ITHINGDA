/**
 * TellerTjener.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er hovedprogrammet på tjenersiden.
 */

import java.rmi.Naming;
class TellerTjener {
  public static void main(String[] args) throws Exception {
    String objektnavn = "Åses_tellebyrå_AS";
    System.out.println("Dette er statusvindu for tjenersiden.");
    TellemaskinFront tellemaskin = new TellemaskinFrontImpl();
    System.out.println("Nå er tellemaskinen laget.");
    Naming.rebind(objektnavn, tellemaskin);
    System.out.println("Nå venter vi bare på at noen skal telle oss opp ...");
    javax.swing.JOptionPane.showMessageDialog(null,
                       "Trykk Ok for å stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}

