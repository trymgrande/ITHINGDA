/**
 * BinaerTreKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klient for bin�rt s�ketre
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
    System.out.println("Treet i sortert rekkef�lge: " + tre.toString());
    System.out.println("Finnes 2 i treet: " + tre.s�kEtterVerdi(2));
    System.out.println("Finnes 6 i treet: " + tre.s�kEtterVerdi(6));
  }
}

/* Kj�ring av programmet:
Treet i sortert rekkef�lge: 1 2 3 4 5 7
Finnes 2 i treet: true
Finnes 6 i treet: false
*/
