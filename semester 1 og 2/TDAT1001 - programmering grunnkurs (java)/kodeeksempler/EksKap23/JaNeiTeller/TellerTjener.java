/**
 * TellerTjener.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Vi lager et objekt med interface JaNeiTeller og registrerer det i rmi-registeret.
 * For enkelthets skyld b�r rmi-registeret v�re startet opp i samme katalog
 * som der tjenerprogrammet ligger.
 */

import java.rmi.Naming;
class TellerTjener {
  public static void main(String[] args) throws Exception {
    String objektnavn = "AS_Tellebyr�";
    System.out.println("Skal lage et tjenerobjekt");
    JaNeiTeller tellemaskin = new JaNeiTellerImpl();  // lager objektet
    System.out.println("N� er det laget!");
    Naming.rebind(objektnavn, tellemaskin);  // registrerer objektet
    System.out.println("N� venter vi bare p� at noen skal telle oss opp ...");
    javax.swing.JOptionPane.showMessageDialog(null,
                       "Trykk Ok for � stoppe tjeneren.");
    Naming.unbind(objektnavn);
    System.exit(0);
  }
}

/* Kj�ring av programmet:
Skal lage et tjenerobjekt
N� er det laget!
N� venter vi bare p� at noen skal telle oss opp ...
N� ble antall ja-stemmer �kt med 1
N� ble antall nei-stemmer �kt med 1
N� ble antall ja-stemmer �kt med 10
N� ble antall nei-stemmer �kt med 20
*/