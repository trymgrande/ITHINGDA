/**
 * Person.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * Person: Data om en person; f�dselsnummer, navn og adresse
 * Student og Ansatt: Subklasser til Person
 */

class Person {
  private final long fnr;
  private final String navn;
  private final String adresse;

  public Person(long fnr, String navn, String adresse) {
    this.fnr = fnr;
    this.navn = navn;
    this.adresse = adresse;
  }

  public long getFnr() {
    return fnr;
  }

  public String getNavn() {
    return navn;
  }

  public String getAdresse() {
    return adresse;
  }

  public String toString() {
    return "f.nr.: " + fnr + ", navn: " + navn + ", adresse: " + adresse;
  }
}

/**
 * Klassen Student - en spesialisering av Person.
 * Studentnr kommer som nytt attributt.
 */
class Student extends Person {
  private final long studnr;

  public Student(long fnr, String navn, String adresse, long studnr) {
    super(fnr, navn, adresse);
    this.studnr = studnr;
  }

  public long getStudnr() {
    return studnr;
  }

  public String toString() {
    return "Student: stud.nr.: " + studnr + " " + super.toString();
  }
}

/**
 * Klassen Ansatt - en spesialisering av Person.
 * L�nnstrinn kommer som nytt attributt.
 */
class Ansatt extends Person {
  private int l�nnstrinn;

  public Ansatt(long fnr, String navn, String adresse,
      int l�nnstrinn) {
    super(fnr, navn, adresse);
    this.l�nnstrinn = l�nnstrinn;
  }

  public int getL�nnstrinn() {
    return l�nnstrinn;
  }

  public String toString() {
    return "Ansatt: l�nnstrinn: " + l�nnstrinn + " " + super.toString();
  }
}