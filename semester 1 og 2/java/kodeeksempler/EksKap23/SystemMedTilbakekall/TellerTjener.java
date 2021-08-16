/**
 * TellerTjener.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er hovedprogrammet p� tjenersiden.
 */

import java.rmi.Naming;
class TellerTjener {
  public static void main(String[] args) throws Exception {
    String objektnavn = "�ses_tellebyr�_AS";
    System.out.println("Dette er statusvindu for tjenersiden.");
    TellemaskinFront tellemaskin = new TellemaskinFrontImpl();
    System.out.println("N� er tellemaskinen laget.");
    Naming.rebind(objektnavn, tellemaskin);
    System.out.println("N� venter vi bare p� at noen skal telle oss opp ...");
    javax.swing.JOptionPane.showMessageDialog(null,
                       "Trykk Ok for � stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}

