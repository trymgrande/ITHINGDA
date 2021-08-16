/**
 * PersonKlientPolymorfi.java   - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Eksempler på polymorfi. Klassene Person, Ansatt og Student er vist foran.
 */

public class PersonKlientPolymorfi {
  public static void main(String[] args) {
    Student student1 = new Student(12106078756L, "Ole Pettersen",
      "Storgt 3, 7001 Trondheim", 1234567L);
    Ansatt læreren = new Ansatt(2307778056L, "Hanne Hansen",
      "Storgt 13, 7001 Trondheim", 40);
    Student student2 = new Student(12106666756L, "Ingrid Hansen",
      "Karl Johans gt 13, 7001 Trondheim", 1222267L);

    /* Samler ulike typer objekter i en Person-tabell */
    Person[] personer = new Person[3];
    personer[0] = student1;
    personer[1] = læreren;
    personer[2] = student2;
    for (int i = 0; i < personer.length; i++) {
      System.out.println(personer[i].toString());
    }

    /*
     * På grunn av at toString() er deklarert i klassen Object, kan vi samle
     * disse (og egentlig alle andre) objektene i en tabell av typen Object.
     */
    Object[] objekter = new Object[3];
    objekter[0] = student1;
    objekter[1] = læreren;
    objekter[2] = student2;
    for (int i = 0; i < objekter.length; i++) {
      System.out.println(objekter[i].toString());
    }
  }
}

/* Utskrift:
Student: stud.nr.: 1234567 f.nr.: 12106078756, navn: Ole Pettersen, adresse: Storgt 3, 7001 Trondheim
Ansatt: lønnstrinn: 40 f.nr.: 2307778056, navn: Hanne Hansen, adresse: Storgt 13, 7001 Trondheim
Student: stud.nr.: 1222267 f.nr.: 12106666756, navn: Ingrid Hansen, adresse: Karl Johans gt 13, 7001 Trondheim
Student: stud.nr.: 1234567 f.nr.: 12106078756, navn: Ole Pettersen, adresse: Storgt 3, 7001 Trondheim
Ansatt: lønnstrinn: 40 f.nr.: 2307778056, navn: Hanne Hansen, adresse: Storgt 13, 7001 Trondheim
Student: stud.nr.: 1222267 f.nr.: 12106666756, navn: Ingrid Hansen, adresse: Karl Johans gt 13, 7001 Trondheim
*/
