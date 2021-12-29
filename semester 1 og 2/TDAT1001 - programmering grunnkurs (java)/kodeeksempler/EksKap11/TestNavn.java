/**
 * TestNavn.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Prøver metodene i klassen Navn
 */
class TestNavn {
  public static void main(String[] args) {
    Navn etNavn = new Navn("Ole Petter", "Engebretsen");
    System.out.println("Hele navnet: " + etNavn.toString());
    System.out.println("Henter bare fornavn: " + etNavn.getFornavn());
    System.out.println("Henter bare etternavn: " + etNavn.getEtternavn());
    etNavn.setFornavn("Ole Anders");
    etNavn.setEtternavn("Lia");
    System.out.println("Navnet etter endring: " + etNavn.toString());
   }
 }

/* Utskrift:
Hele navnet: Ole Petter Engebretsen
Henter bare fornavn: Ole Petter
Henter bare etternavn: Engebretsen
Navnet etter endring: Ole Anders Lia
*/