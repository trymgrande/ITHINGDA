/**
 * PersonKlient.java    - "Programmering i Java", 4.utgave - 2009-07-01
 * Enkelt klientprogram som viser bruk av toString() og arv av metoder
 * mot klassen Person med subklasser.
 */
 class PersonKlient {
  public static void main(String[] args) {
    Student studenten =
      new Student(12106078756L, "Ole B�", "Storgt 3, 2345 Os", 1234567L);
    Ansatt l�reren =
      new Ansatt(5107078056L, "Hanne Hansen", "Lia, 3456 Mo", 40);
    System.out.println("Utskrift, toString():\n" + studenten + "\n" + l�reren);
    System.out.println("\nUtskrift, get-metoder, de fleste arvet:");
    System.out.println("Studenten: " + studenten.getFnr() + ", " + studenten.getNavn()
       + ", " + studenten.getAdresse() + ", stud.nr.: " + studenten.getStudnr());
    System.out.println("L�reren: " + " " + l�reren.getFnr() + ", " + l�reren.getNavn()
       + ", " + l�reren.getAdresse() + ", l�nnstrinn " + l�reren.getL�nnstrinn());
  }
}

/* Utskrift:
Utskrift, toString():
Student: stud.nr.: 1234567 f.nr.: 12106078756, navn: Ole B�, adresse: Storgt 3, 2345 Os
Ansatt: l�nnstrinn: 40 f.nr.: 5107078056, navn: Hanne Hansen, adresse: Lia, 3456 Mo

Utskrift, get-metoder, de fleste arvet:
Studenten: 12106078756, Ole B�, Storgt 3, 2345 Os, stud.nr.: 1234567
L�reren:  5107078056, Hanne Hansen, Lia, 3456 Mo, l�nnstrinn 40
*/
*/