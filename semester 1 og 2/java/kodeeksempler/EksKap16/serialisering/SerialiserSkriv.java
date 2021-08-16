/**
 * SerialiserSkriv.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Oppretter objekter som lagres serialisert på datafil.
 * Klassene objektene tilhører, er gjennomgått i kapittel 13.6, side 431-439
 */

import java.io.*;
class SerialiserSkriv {
  public static void main(String[] args) throws IOException {
    Maling m1 = new Maling("Heimdal Extra", 100, 3, 10);
    Maling m2 = new Maling("Heimdal Super", 80, 2, 12);
    Flate f1 = new Flate("Vegg i barnerom", 4, 3);
    Flate f2 = new Flate("Taket i gangen", 6, 3);
    f1.setMalingstype(m1);
    f2.setMalingstype(m2);

    Oppussingsprosjekt enLeilighet = new Oppussingsprosjekt("Min leilighet");
    enLeilighet.registrerNyMaling(m1);
    enLeilighet.registrerNyMaling(m2);
    enLeilighet.registrerNyFlate(f1);
    enLeilighet.registrerNyFlate(f2);

    /* Vi lagrer ett stort objekt: */
    FileOutputStream utstrøm = new FileOutputStream("leilighet1.ser");
    ObjectOutputStream ut = new ObjectOutputStream(utstrøm);
    ut.writeObject(enLeilighet);
    ut.close();

    /* Vi lagrer flere mindre objekter */
    utstrøm = new FileOutputStream("leilighet2.ser");
    ut = new ObjectOutputStream(utstrøm);
    ut.writeObject(f1);
    ut.writeObject(f2);
    ut.writeObject(m1);
    ut.writeObject(m2);
    ut.close();
  }
}

