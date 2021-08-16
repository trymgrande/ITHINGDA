/**
 * JaNeiTeller.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen gj�r det mulig � telle antall ja- og nei-stemmer.
 *
 */

class JaNeiTeller {
  private int antallJa = 0;
  private int antallNei = 0;

  public void �kAntallJa() {
    antallJa++;
  }

  public void �kAntallNei() {
    antallNei++;
  }

  public void �kAntallJa(int �kning) {
    antallJa += �kning;
  }

  public void �kAntallNei(int �kning) {
    antallNei += �kning;
  }

  public int getAntallJa() {
    return antallJa;
  }

  public int getAntallNei() {
    return antallNei;
  }

  public String toString() {
    return "Antall ja: " + antallJa + ", antall nei: " + antallNei;
  }
}