/**
 * Konto.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen beskriver en bankkonto med nr., navn og saldo.
 * Det er mulig � utf�re transaksjoner mot kontoen.
 */

class Konto {
  private final long kontonr;
  private final String navn;
  private double saldo;

  public Konto(long kontonr, String navn, double saldo) {
    this.kontonr = kontonr;
    this.navn = navn;
    this.saldo = saldo;
  }

  public long getKontonr() {
    return kontonr;
  }

  public String getNavn() {
    return navn;
  }

  public double getSaldo() {
    return saldo;
  }

  public boolean utf�rTransaksjon(double bel�p) {
    if (saldo + bel�p >= 0) {
      saldo += bel�p;
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    final double TOLERANSE = 0.001;
    System.out.println("Totalt antall tester: 4");

    Konto kontoOle = new Konto(123456676756L, "Ole Olsen", 0.0);

    if (kontoOle.getKontonr() == 123456676756L &&    // pr�ver get-metodene
        kontoOle.getNavn().equals("Ole Olsen") &&
        kontoOle.getSaldo() == 0.0) {
      System.out.println("Konto: Test 1 vellykket");
    }
    boolean transOk = kontoOle.utf�rTransaksjon(1000);   // setter inn et bel�p
    double nySaldo = kontoOle.getSaldo();
    if (transOk && Math.abs(nySaldo - 1000) < TOLERANSE) {
      System.out.println("Konto: Test 2 vellykket");
    }

    Konto kontoPer = new Konto(223456676756L, "Per Olsen", 3000.0);
    transOk = kontoPer.utf�rTransaksjon(-1000);   // tar ut et bel�p
    nySaldo = kontoPer.getSaldo();
    if (transOk && Math.abs(nySaldo - 2000) < TOLERANSE) {
      System.out.println("Konto: Test 3 vellykket");
    }

    Konto kontoEva = new Konto(323456676756L, "Eva Olsen", 0.0);
    transOk = kontoEva.utf�rTransaksjon(-100);   // fors�ker � overtrekke konto
    nySaldo = kontoEva.getSaldo();
    if (!transOk && Math.abs(nySaldo - 0) < TOLERANSE) {
      System.out.println("Konto: Test 4 vellykket");
    }
  }
}

/* Utskrift:
Totalt antall tester: 4
Konto: Test 1 vellykket
Konto: Test 2 vellykket
Konto: Test 3 vellykket
Konto: Test 4 vellykket
*/