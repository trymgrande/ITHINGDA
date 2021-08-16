/**
 * SerialiserLes.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Leser serialiserte objekter.
 * Objektene ble laget med programmet p� filen SerialiserSkriv.java.
 */

import java.io.*;
class SerialiserLes {
  public static void main(String[] args) throws Exception {
    FileInputStream innstr�m = new FileInputStream("leilighet1.ser");
    ObjectInputStream inn = new ObjectInputStream(innstr�m);
    Oppussingsprosjekt enLeilighet = (Oppussingsprosjekt)inn.readObject();
    inn.close();

    System.out.println("Leilighetsdata lest fra filen leilighet1.ser:");
    for (int i = 0; i < enLeilighet.finnAntFlater(); i++) {
      System.out.println(enLeilighet.finnFlate(i)); // toString() er underforst�tt
    }

    for (int i = 0; i < enLeilighet.finnAntMalinger(); i++) {
      System.out.println(enLeilighet.finnMaling(i));
    }

    System.out.println("Flate og malingsdata, lest fra leilighet2.ser:");
    innstr�m = new FileInputStream("leilighet2.ser");
    inn = new ObjectInputStream(innstr�m);
    try {
      while (true) {  // stopper n�r EOFException kastes
        Object obj = inn.readObject();
        System.out.println(obj); // riktig toString() p� grunn av polymorfi
      }
    }
    catch (EOFException e) {
    }
    inn.close();
  }
}

/* Utskrift fra programmet:
Leilighetsdata lest fra filen leilighet1.ser:
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m.
Flate: Taket i gangen, bredde: 3,00 m, lengde: 6,00 m.
Maling: Heimdal Extra, pris kr 100,00 pr. liter, ant. str�k: 3, ant. kvm/l: 10,00
Maling: Heimdal Super, pris kr 80,00 pr. liter, ant. str�k: 2, ant. kvm/l: 12,00
Flate og malingsdata, lest fra leilighet2.ser
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m.
Flate: Taket i gangen, bredde: 3,00 m, lengde: 6,00 m.
Maling: Heimdal Extra, pris kr 100,00 pr. liter, ant. str�k: 3, ant. kvm/l: 10,00
Maling: Heimdal Super, pris kr 80,00 pr. liter, ant. str�k: 2, ant. kvm/l: 12,00
*/
