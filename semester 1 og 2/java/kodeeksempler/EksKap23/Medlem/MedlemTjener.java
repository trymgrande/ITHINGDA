/**
 * MedlemTjener.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Lager et fabrikkobjekt som klienten kan bruke til � f� laget objekter her p�
 * tjenersiden. Rmi-registeret m� v�re startet opp i samme mappe som
 * dette programmet ligger.
 */

import java.rmi.Naming;
class MedlemTjener {
  public static void main(String[] args) throws Exception {
    String objektnavn = "Medlemsfabrikk";
    Medlemsfabrikk fabrikkobjekt = new MedlemsfabrikkImpl();  // lager objektet
    Naming.rebind(objektnavn, fabrikkobjekt);  // registrerer det i rmi-registeret
    System.out.println("N� venter vi bare p� at noen skal lage medlemsobjekter ...");
    javax.swing.JOptionPane.showMessageDialog(null,
                           "Trykk Ok for � stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}

/* Kj�ring av programmet:
N� venter vi bare p� at noen skal lage medlemsobjekter ...
*/