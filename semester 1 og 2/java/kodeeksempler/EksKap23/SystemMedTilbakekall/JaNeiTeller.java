/**
 * JaNeiTeller.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen gjør det mulig å telle antall ja- og nei-stemmer.
 *
 */

class JaNeiTeller {
  private int antallJa = 0;
  private int antallNei = 0;

  public void økAntallJa() {
    antallJa++;
  }

  public void økAntallNei() {
    antallNei++;
  }

  public void økAntallJa(int økning) {
    antallJa += økning;
  }

  public void økAntallNei(int økning) {
    antallNei += økning;
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