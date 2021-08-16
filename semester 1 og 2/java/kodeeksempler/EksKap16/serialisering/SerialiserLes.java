/**
 * SerialiserLes.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Leser serialiserte objekter.
 * Objektene ble laget med programmet på filen SerialiserSkriv.java.
 */

import java.io.*;
class SerialiserLes {
  public static void main(String[] args) throws Exception {
    FileInputStream innstrøm = new FileInputStream("leilighet1.ser");
    ObjectInputStream inn = new ObjectInputStream(innstrøm);
    Oppussingsprosjekt enLeilighet = (Oppussingsprosjekt)inn.readObject();
    inn.close();

    System.out.println("Leilighetsdata lest fra filen leilighet1.ser:");
    for (int i = 0; i < enLeilighet.finnAntFlater(); i++) {
      System.out.println(enLeilighet.finnFlate(i)); // toString() er underforstått
    }

    for (int i = 0; i < enLeilighet.finnAntMalinger(); i++) {
      System.out.println(enLeilighet.finnMaling(i));
    }

    System.out.println("Flate og malingsdata, lest fra leilighet2.ser:");
    innstrøm = new FileInputStream("leilighet2.ser");
    inn = new ObjectInputStream(innstrøm);
    try {
      while (true) {  // stopper når EOFException kastes
        Object obj = inn.readObject();
        System.out.println(obj); // riktig toString() på grunn av polymorfi
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
Maling: Heimdal Extra, pris kr 100,00 pr. liter, ant. strøk: 3, ant. kvm/l: 10,00
Maling: Heimdal Super, pris kr 80,00 pr. liter, ant. strøk: 2, ant. kvm/l: 12,00
Flate og malingsdata, lest fra leilighet2.ser
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m.
Flate: Taket i gangen, bredde: 3,00 m, lengde: 6,00 m.
Maling: Heimdal Extra, pris kr 100,00 pr. liter, ant. strøk: 3, ant. kvm/l: 10,00
Maling: Heimdal Super, pris kr 80,00 pr. liter, ant. strøk: 2, ant. kvm/l: 12,00
*/
