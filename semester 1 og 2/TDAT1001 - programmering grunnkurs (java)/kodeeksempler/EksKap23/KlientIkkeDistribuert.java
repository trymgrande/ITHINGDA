/**
 * KlientIkkeDistribuert.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En liten klient som prøver klassen JaNeiTeller.
 *
 */
class KlientIkkeDistribuert {
  public static void main(String[] args) {
    JaNeiTeller tellemaskin = new JaNeiTeller();
    tellemaskin.økAntallJa();
    tellemaskin.økAntallNei();
    System.out.println(tellemaskin);
    tellemaskin.økAntallJa(10);
    tellemaskin.økAntallNei(20);
    System.out.println(tellemaskin);
  }
}
/* Utskrift:
Antall ja: 1 Antall nei: 1
Antall ja: 11 Antall nei: 21
*/
