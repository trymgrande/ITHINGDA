/**
 * Konto2.java -  "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En utgave av klassen Konto der vi bruker �re i stedet for kroner
 * for � utnytte fordelene med heltallsberegninger.
 * Grensesnittet mot omverdenen er det samme.
 */

class Konto2 {
  private final long kontonr;
  private final String navn;
  private long saldo; // �re

  public Konto2(long kontonr, String navn, double saldo) {
    this.kontonr = kontonr;
    this.navn = navn;
    this.saldo = regnOmTil�re(saldo);
  }

  public long getKontonr() {
    return kontonr;
  }

  public String getNavn() {
    return navn;
  }

  public double getSaldo() {
    double saldoKr = regnOmTilKr(saldo);
    return saldoKr;
  }

  public boolean utf�rTransaksjon(double bel�p) {
    int bel�p�re = regnOmTil�re(bel�p);
    if (saldo + bel�p�re >= 0) {
      saldo += bel�p�re;
      return true;
    } else {
      return false;
    }
  }

  /* Hjelpemetoder */
  private int regnOmTil�re(double kr) {
    return (int) (kr * 100);
  }

  private double regnOmTilKr(long �re) {
    return �re * 0.01;
  }

  public static void main(String[] args) {
    final double TOLERANSE = 0.001;
    System.out.println("Totalt antall tester: 3");

    Konto2 kontoOle = new Konto2(123456676756L, "Ole Olsen", 0);
    boolean transOk = kontoOle.utf�rTransaksjon(1000);   // setter inn et bel�p
    double nySaldo = kontoOle.getSaldo();
    if (transOk && Math.abs(nySaldo - 1000) < TOLERANSE) {
      System.out.println("Konto2: Test 1 vellykket");
    }

    Konto2 kontoPer = new Konto2(223456676756L, "Per Olsen", 3000);
    transOk = kontoPer.utf�rTransaksjon(-1000);   // tar ut et bel�p
    nySaldo = kontoPer.getSaldo();
    if (transOk && Math.abs(nySaldo - 2000) < TOLERANSE) {
      System.out.println("Konto2: Test 2 vellykket");
    }

    Konto2 kontoEva = new Konto2(323456676756L, "Eva Olsen", 0);
    transOk = kontoEva.utf�rTransaksjon(-100);   // fors�ker � overtrekke konto
    nySaldo = kontoEva.getSaldo();
    if (!transOk && Math.abs(nySaldo - 0) < TOLERANSE) {
      System.out.println("Konto2: Test 3 vellykket");
    }
  }
}

