/**
 * Konto.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen beskriver en bankkonto med nr., navn og saldo.
 * Det er mulig å utføre transaksjoner mot kontoen.
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

  public void utførTransaksjon(double beløp) {
    saldo += beløp;
  }
}

