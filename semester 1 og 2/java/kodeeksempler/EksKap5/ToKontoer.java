/**
 * ToKontoer.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Oppretter kontoobjekter for Per og Ole og overfører kr 1000 fra Ole til Per.
 */

class ToKontoer {
  public static void main(String[] args) {

    /* Oppretter to objekter, ett for Ole og ett for Per */
    Konto olesKonto = new Konto(123456676756L, "Ole Olsen", 2300.50);
    Konto persKonto = new Konto(223456676756L, "Per Hansen", 5000);

    /* Overfører kr 1000 fra Ole til Per */
    olesKonto.utførTransaksjon(-1000.0);
    persKonto.utførTransaksjon(1000.0);

    /* Henter ut de nye saldoene */
    double saldoOle = olesKonto.getSaldo();
    double saldoPer = persKonto.getSaldo();
    System.out.println("Etter transaksjonen er Oles saldo lik " + saldoOle);
    System.out.println("Etter transaksjonen er Pers saldo lik " + saldoPer);
  }
}

/* Utskrift:
Etter transaksjonen er Oles saldo lik 1300.5
Etter transaksjonen er Pers saldo lik 6000.0
*/