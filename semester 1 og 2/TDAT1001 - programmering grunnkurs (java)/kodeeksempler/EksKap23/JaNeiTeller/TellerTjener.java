/**
 * TellerTjener.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Vi lager et objekt med interface JaNeiTeller og registrerer det i rmi-registeret.
 * For enkelthets skyld bør rmi-registeret være startet opp i samme katalog
 * som der tjenerprogrammet ligger.
 */

import java.rmi.Naming;
class TellerTjener {
  public static void main(String[] args) throws Exception {
    String objektnavn = "AS_Tellebyrå";
    System.out.println("Skal lage et tjenerobjekt");
    JaNeiTeller tellemaskin = new JaNeiTellerImpl();  // lager objektet
    System.out.println("Nå er det laget!");
    Naming.rebind(objektnavn, tellemaskin);  // registrerer objektet
    System.out.println("Nå venter vi bare på at noen skal telle oss opp ...");
    javax.swing.JOptionPane.showMessageDialog(null,
                       "Trykk Ok for å stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}

/* Kjøring av programmet:
Skal lage et tjenerobjekt
Nå er det laget!
Nå venter vi bare på at noen skal telle oss opp ...
Nå ble antall ja-stemmer økt med 1
Nå ble antall nei-stemmer økt med 1
Nå ble antall ja-stemmer økt med 10
Nå ble antall nei-stemmer økt med 20
*/