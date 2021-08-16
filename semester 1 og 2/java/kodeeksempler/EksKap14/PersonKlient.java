/**
 * PersonKlient.java    - "Programmering i Java", 4.utgave - 2009-07-01
 * Enkelt klientprogram som viser bruk av toString() og arv av metoder
 * mot klassen Person med subklasser.
 */
 class PersonKlient {
  public static void main(String[] args) {
    Student studenten =
      new Student(12106078756L, "Ole Bø", "Storgt 3, 2345 Os", 1234567L);
    Ansatt læreren =
      new Ansatt(5107078056L, "Hanne Hansen", "Lia, 3456 Mo", 40);
    System.out.println("Utskrift, toString():\n" + studenten + "\n" + læreren);
    System.out.println("\nUtskrift, get-metoder, de fleste arvet:");
    System.out.println("Studenten: " + studenten.getFnr() + ", " + studenten.getNavn()
       + ", " + studenten.getAdresse() + ", stud.nr.: " + studenten.getStudnr());
    System.out.println("Læreren: " + " " + læreren.getFnr() + ", " + læreren.getNavn()
       + ", " + læreren.getAdresse() + ", lønnstrinn " + læreren.getLønnstrinn());
  }
}

/* Utskrift:
Utskrift, toString():
Student: stud.nr.: 1234567 f.nr.: 12106078756, navn: Ole Bø, adresse: Storgt 3, 2345 Os
Ansatt: lønnstrinn: 40 f.nr.: 5107078056, navn: Hanne Hansen, adresse: Lia, 3456 Mo

Utskrift, get-metoder, de fleste arvet:
Studenten: 12106078756, Ole Bø, Storgt 3, 2345 Os, stud.nr.: 1234567
Læreren:  5107078056, Hanne Hansen, Lia, 3456 Mo, lønnstrinn 40
*/
*/