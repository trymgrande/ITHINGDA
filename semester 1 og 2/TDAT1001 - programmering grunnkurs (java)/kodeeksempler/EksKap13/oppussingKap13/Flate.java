/**
 * Flate.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Flate tilbyr metoder for beregning av areal og omkrets.
 */

class Flate {
  private final String navn;  // til identifikasjon
  private final double lengde;
  private final double bredde;
  private Maling malingstype;

  /**
   * Konstruktøren kaster IllegalArgumentException hvis ugyldige argumenter.
   */
  public Flate(String navn, double lengde, double bredde) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Flatens navn må være oppgitt.");
    }
    if (lengde <= 0.0 || bredde <= 0.0) {
      throw new IllegalArgumentException(
                "Både bredde og lengde må være positive tall.\n" +
                "Inndata til konstruktøren var: lengde = " + lengde +
                ", bredde = " + bredde);
    }
    this.navn = navn;
    this.lengde = lengde;
    this.bredde = bredde;
  }

  public String getNavn() {
    return navn;
  }

  public double getLengde() {
    return lengde;
  }

  public double getBredde() {
    return bredde;
  }

  public double beregnAreal() {
    return bredde * lengde;
  }

  public double beregnOmkrets() {
    return 2.0 * (lengde + bredde);
  }

public void setMalingstype(Maling nyMalingstype) {
  malingstype = nyMalingstype;
}

  public Maling getMalingstype() {
    return malingstype;
  }

  public boolean equals(Object denAndre) { // metodehodet må være eksakt slik
    if (!(denAndre instanceof Flate)) {
      return false; // RETUR. Raskt uthopp hvis feil objekttype.
    }
    if (this == denAndre) {
      return true; // RETUR. Raskt uthopp hvis samme objekt.
    }
    Flate flate2 = (Flate) denAndre;
    return (navn.equals(flate2.navn)); // RETUR. Sammenligner navn.
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Flate: %s, bredde: %.2f m, lengde: %.2f m.", navn, bredde, lengde);
    return f.toString();
  }

  public static void main(String[] args) {
    final double TOLERANSE = 0.001;
    System.out.println("Totalt antall tester: 2");
    Flate f1 = new Flate("A", 12.5, 7.3); // areal 91,25 - omkrets 39,6
    if (Math.abs(f1.beregnAreal() - 91.25) < TOLERANSE) {
      System.out.println("Flate: Test 1 vellykket");
    }
    if (Math.abs(f1.beregnOmkrets() - 39.6) < TOLERANSE) {
      System.out.println("Flate: Test 2 vellykket");
    }
  }
}