/**
 * TestTreLag.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Kontakter databasen via fjernobjekt og databasepool.
 */

import java.rmi.Naming;
import java.util.ArrayList;
import mittBibliotek.database.Database;
import mittBibliotek.database.DbWrapperFabrikk;
import mittBibliotek.Person;

class TestTreLag {
  public static void main(String[] args) throws Exception {
    DbWrapperFabrikk dbForb = (DbWrapperFabrikk) Naming.lookup("rmi://localhost/Persondatabase");
    Database db = dbForb.lagDbWrapper();
    ArrayList<Person> alle = db.finnAlle();
    if (alle != null) {
      for (Person p : alle) {
        System.out.println(p);
      }
    } else {
      System.out.println("Databasen er tom");
    }
  }
}

/*
Utskrift på tjenersiden:
select persnr, fornavn, etternavn from person order by etternavn, fornavn
Reserverer forbindelse 0
Frigir forbindelse 0.

Utskrift på klientsiden:
102: Hansen, Jonny
100: Hansen, Ole
101: Ås, Anne Grethe
*/