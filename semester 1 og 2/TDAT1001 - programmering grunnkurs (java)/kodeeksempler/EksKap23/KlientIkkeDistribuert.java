/**
 * KlientIkkeDistribuert.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En liten klient som pr�ver klassen JaNeiTeller.
 *
 */
class KlientIkkeDistribuert {
  public static void main(String[] args) {
    JaNeiTeller tellemaskin = new JaNeiTeller();
    tellemaskin.�kAntallJa();
    tellemaskin.�kAntallNei();
    System.out.println(tellemaskin);
    tellemaskin.�kAntallJa(10);
    tellemaskin.�kAntallNei(20);
    System.out.println(tellemaskin);
  }
}
/* Utskrift:
Antall ja: 1 Antall nei: 1
Antall ja: 11 Antall nei: 21
*/
