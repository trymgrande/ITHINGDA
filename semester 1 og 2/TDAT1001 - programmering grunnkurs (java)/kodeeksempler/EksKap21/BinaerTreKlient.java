/**
 * BinaerTreKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klient for binært søketre
 */

class BinaerTreKlient {
  public static void main(String[] args) {
    BinaertSoekeTre tre = new BinaertSoekeTre();
    tre.settInnVerdi(5);
    tre.settInnVerdi(3);
    tre.settInnVerdi(1);
    tre.settInnVerdi(2);
    tre.settInnVerdi(4);
    tre.settInnVerdi(7);
    System.out.println("Treet i sortert rekkefølge: " + tre.toString());
    System.out.println("Finnes 2 i treet: " + tre.søkEtterVerdi(2));
    System.out.println("Finnes 6 i treet: " + tre.søkEtterVerdi(6));
  }
}

/* Kjøring av programmet:
Treet i sortert rekkefølge: 1 2 3 4 5 7
Finnes 2 i treet: true
Finnes 6 i treet: false
*/
