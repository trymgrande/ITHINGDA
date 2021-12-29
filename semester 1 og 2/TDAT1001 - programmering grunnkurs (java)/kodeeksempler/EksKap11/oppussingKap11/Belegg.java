/**
 * Belegg.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Belegg tilbyr metoder for beregning av materialbehov og pris.
 * For � f� til dette m� et objekt av klassen samarbeide med
 * et objekt av klassen Flate.
 */

class Belegg {
  /* Grense for � beregne en bredde ekstra. */
  private static final double GRENSE = 0.02;

  private final String navn;  // identifiserer belegget
  private final double prisPrM;  // pris pr. meter
  private final double bredde;    // meter

  /**
   * Konstrukt�ren kaster IllegalArgumentException hvis ugyldige argumenter.
   */
  public Belegg(String navn, double prisPrM, double bredde) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Beleggets navn m� v�re oppgitt.");
    }
    if (prisPrM <= 0.0 || bredde <= 0.0) {
      throw new IllegalArgumentException(
                "B�de pris og bredde m� v�re positive tall.\n" +
                "Inndata til konstrukt�ren var: pris = " + prisPrM + ", bredde = " + bredde);
    }
    this.navn = navn;
    this.prisPrM = prisPrM;
    this.bredde = bredde;
  }

  public String getNavn() {
    return navn;
  }

  public double getPrisPrM() {
    return prisPrM;
  }

  public double getBredde() {
    return bredde;
  }

  /**
   * Skal beregne antall meter som trengs til en flate.
   * Teppet legges alltid p� tvers av golvets lengde.
   * For � finne ut om det er billigere � legge teppet andre veien,
   * m� klienten bytte om lengde og bredde i den flaten som
   * sendes inn som argument.
   */
  public double beregnAntMeter(Flate flaten) {
    double lengdeFlate = flaten.getLengde();
    double breddeFlate = flaten.getBredde();

    int antBredder = (int)(lengdeFlate / bredde);
    double rest = lengdeFlate % bredde;
    if (rest >= GRENSE) {
      antBredder++;
    }
    return antBredder * breddeFlate ;
  }

  /**
   * Beregner hva det koster � belegge en bestemt flate med dette belegget.
   */
  public double beregnTotalpris(Flate flaten) {
    return beregnAntMeter(flaten) * prisPrM;
  }

  /**
   * To belegg defineres som identiske hvis de har samme navn.
   */
  public boolean equals(Object detAndre) {
    if (!(detAndre instanceof Belegg)) {
      return false;  // RETUR. Raskt uthopp hvis feil objekttype.
    }
    if (this == detAndre) {
      return true;  // RETUR. Raskt uthopp hvis samme objekt.
    }
    Belegg belegg = (Belegg) detAndre;
    return (navn.equals(belegg.navn));  // RETUR. Sammenligner navn.
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Belegg: %s, pris kr %.2f pr. m, bredde: %.2f m.", navn, prisPrM, bredde);
    return f.toString();
  }
}