/**
 * MedlemKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Program som kommuniserer med objekter med interface Medlem og
 * Medlemsfabrikk. Hensikten er � demonstrere hvordan overf�ring av argumenter
 * og returverdier skjer i RMI.
 */

import java.rmi.Naming;
import mittBibliotek.Person;
class MedlemKlient {
  public static void main(String[] args) throws Exception {
    String url = "rmi://localhost/Medlemsfabrikk";
    Medlemsfabrikk fabrikkobjekt = (Medlemsfabrikk) Naming.lookup(url);
    Person person1 = new Person(200, "Inger", "Hansen");

    /* Lager (fjern)objekt p� tjenersiden */
    Medlem medlem = fabrikkobjekt.lagMedlem(person1, "7001 Trondheim");

    person1.setEtternavn("Olsen");  // endrer ikke data i fjernobjektet
    Person person2 = medlem.getPerson();
    System.out.println(person2); // bruker toString()
    person2.setEtternavn("Jensen"); // endrer ikke data i fjernobjektet
    medlem.setPerson(person2);  // n� endrer vi data i fjernobjektet
    Person person3 = medlem.getPerson();
    System.out.println(person3);
  }
}

/* Kj�ring av programmet:
200: Hansen, Inger
200: Jensen, Inger
*/

