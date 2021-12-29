/**
 * Flate.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Flate tilbyr metoder for beregning av areal og omkrets.
 */

class Flate implements Comparable<Flate>{
  private final String navn;  // til identifikasjon
  private final double lengde;
  private final double bredde;
  public final static double TOLERANSE = 0.001;
  private final static java.text.Collator KOLLATOR = java.text.Collator.getInstance();

  /**
   * Konstruktøren kaster IllegalArgumentException hvis ugyldige argumenter.
   */
  public Flate(String navn, double lengde, double bredde)
                       throws IllegalArgumentException {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Flatens navn må være oppgitt.");
    }
    if (lengde <= 0.0 || bredde <= 0.0) {
      throw new IllegalArgumentException(
                "Både lengde og bredde må være positive tall.\n" +
                "Inndata til konstruktøren var: lengde = " + lengde + ", bredde = " + bredde);
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

  public int compareTo(Flate denAndre) {
    return KOLLATOR.compare(navn, denAndre.navn);
  }

  /**
   * To flater defineres som identiske hvis de har samme navn.
   */
  public boolean equals(Object denAndre) {
    if (!(denAndre instanceof Flate)) {
      return false;  // RETUR. Raskt uthopp hvis feil objekttype.
    }
    if (this == denAndre) {
      return true;  // RETUR. Raskt uthopp hvis samme objekt.
    }
    return compareTo((Flate) denAndre) == 0; // konsistens med compareTo()
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Flate: %s, bredde: %.2f m, lengde: %.2f m.", navn, bredde, lengde);
    return f.toString();
  }

  /**
   * Tester klassen Flate
   */
  public static void main(String[] args) {
    final double TOLERANSE = 0.001;
    System.out.println("Totalt antall tester: 4");
    Flate f1 = new Flate("A", 12.5, 7.3); // areal 91,25 - omkrets 39,6
    if (Math.abs(f1.beregnAreal() - 91.25) < TOLERANSE) {
      System.out.println("Flate: Test 1 vellykket");
    }
    if (Math.abs(f1.beregnOmkrets() - 39.6) < TOLERANSE) {
      System.out.println("Flate: Test 2 vellykket");
    }
    Flate f2 = new Flate("A", 20.5, 43);
    if (f1.equals(f2)) {  // likhet - samme navn (ulike ellers, men det registreres ikke)
      System.out.println("Flate: Test 3 vellykket");
    }
    Flate f3 = new Flate("B", 20.5, 43);
    if (!f2.equals(f3)) {  // ulikhet
      System.out.println("Flate: Test 4 vellykket");
    }
  }
}