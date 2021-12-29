/**
 * EksempelStudent4.java   - "Programmering i Java", 4.utgave - 2011-08-02
 *
 * Klienten forholder seg til klassen Navn.
 * Studentobjektet og klienten jobber med hver sine kopier av navneobjektene.
 * Dette er programmering etter komposisjonsprinsippet.
 */

class Student {
  private Navn navn;
  private final int fdato;

  public Student(Navn navn, int fdato) {
    /* Vi lar studentobjektet få sin egen kopi av navneobjektet. */
    String fornavn = navn.getFornavn();
    String etternavn = navn.getEtternavn();
    this.navn = new Navn(fornavn, etternavn);
    this.fdato = fdato;
  }

  public int getFdato() {
    return fdato;
  }

  public Navn getNavn() {
    /* Returnerer en kopi. */
    return new Navn(navn.getFornavn(), navn.getEtternavn());
  }

  public void setNavn(Navn navn) {
    /* Vi kopierer data fra det navneobjektet som kommer inn. */
    String nyttFornavn = navn.getFornavn();
    String nyttEtternavn = navn.getEtternavn();
    this.navn.setFornavn(nyttFornavn);
    this.navn.setEtternavn(nyttEtternavn);
  }

  public String toString() {
    return navn + ", født: " + fdato;
  }
}

/**
 * Enkel klient som fokuserer på å vise at et studentobjekt har sitt eget navneobjekt,
 * som klienten ikke har tilgang til.
 */
class EksempelStudent4 {
  public static void main(String[] args) {
    /* Oppretter et navneobjekt som er argument til Student-konstruktøren. */
    Navn studNavn = new Navn("Ole Andreas", "Thomassen");
    Student studenten = new Student(studNavn, 19801010);
    /* Kontrollerer at navnet ble rett registrert. */
    System.out.println("A: Student " + studenten.toString());

    /* Endrer klientens navneobjekt. */
    studNavn.setFornavn("Ingolf");
    /* Studentobjektet skal ikke være endret. */
    System.out.println("B: Student " + studenten.toString());

    /* Klienten henter ut (sin egen kopi av) studentobjektets navn. */
    Navn mittNavn = studenten.getNavn();
    mittNavn.setFornavn("Kåre");  // endrer klientens kopi
    System.out.println("C: Student " + studenten.toString());

    /* Endrer studentobjektets navn. */
    studenten.setNavn(new Navn("Ole Andreas", "Haug"));
    System.out.println("D: Student " + studenten.toString());
  }
}

/* Kjøring av programmet:
A: Student Ole Andreas Thomassen, født: 19801010
B: Student Ole Andreas Thomassen, født: 19801010
C: Student Ole Andreas Thomassen, født: 19801010
D: Student Ole Andreas Haug, født: 19801010
*/