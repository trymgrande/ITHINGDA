/**
 * Konto2.java -  "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En utgave av klassen Konto der vi bruker øre i stedet for kroner
 * for å utnytte fordelene med heltallsberegninger.
 * Grensesnittet mot omverdenen er det samme.
 */

class Konto2 {
  private final long kontonr;
  private final String navn;
  private long saldo; // øre

  public Konto2(long kontonr, String navn, double saldo) {
    this.kontonr = kontonr;
    this.navn = navn;
    this.saldo = regnOmTilØre(saldo);
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

  public boolean utførTransaksjon(double beløp) {
    int beløpØre = regnOmTilØre(beløp);
    if (saldo + beløpØre >= 0) {
      saldo += beløpØre;
      return true;
    } else {
      return false;
    }
  }

  /* Hjelpemetoder */
  private int regnOmTilØre(double kr) {
    return (int) (kr * 100);
  }

  private double regnOmTilKr(long øre) {
    return øre * 0.01;
  }

  public static void main(String[] args) {
    final double TOLERANSE = 0.001;
    System.out.println("Totalt antall tester: 3");

    Konto2 kontoOle = new Konto2(123456676756L, "Ole Olsen", 0);
    boolean transOk = kontoOle.utførTransaksjon(1000);   // setter inn et beløp
    double nySaldo = kontoOle.getSaldo();
    if (transOk && Math.abs(nySaldo - 1000) < TOLERANSE) {
      System.out.println("Konto2: Test 1 vellykket");
    }

    Konto2 kontoPer = new Konto2(223456676756L, "Per Olsen", 3000);
    transOk = kontoPer.utførTransaksjon(-1000);   // tar ut et beløp
    nySaldo = kontoPer.getSaldo();
    if (transOk && Math.abs(nySaldo - 2000) < TOLERANSE) {
      System.out.println("Konto2: Test 2 vellykket");
    }

    Konto2 kontoEva = new Konto2(323456676756L, "Eva Olsen", 0);
    transOk = kontoEva.utførTransaksjon(-100);   // forsøker å overtrekke konto
    nySaldo = kontoEva.getSaldo();
    if (!transOk && Math.abs(nySaldo - 0) < TOLERANSE) {
      System.out.println("Konto2: Test 3 vellykket");
    }
  }
}

