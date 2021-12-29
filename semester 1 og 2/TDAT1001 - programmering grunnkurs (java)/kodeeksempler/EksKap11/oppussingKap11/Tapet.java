/**
 * Tapet.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Tapet tilbyr metoder for beregning av materialbehov og pris.
 *
 */
class Tapet {
  private static final double GRENSE = 0.02; // grense for å beregne en rull ekstra
  private String navn;   // identifiserer tapeten
  private double prisPrRull;   // pris pr. rull
  private double lengdePrRull;    // meter
  private double breddePrRull;    // meter

  /**
   * Konstruktøren kaster IllegalArgumentException hvis ugyldige argumenter.
   */
  public Tapet(String navn, double prisPrRull, double lengdePrRull, double breddePrRull) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Beleggets navn må være oppgitt.");
    }
    if (prisPrRull <= 0.0 || lengdePrRull <= 0.0 || breddePrRull <= 0.0) {
      throw new IllegalArgumentException(
                "Både pris, bredde og lengde må være positive tall.\n" +
                "Inndata til konstruktøren var: pris = " + prisPrRull +
                ", lengde = " + lengdePrRull + ", bredde = " + breddePrRull);
    }
    this.navn = navn;
    this.prisPrRull = prisPrRull;
    this.lengdePrRull = lengdePrRull;
    this.breddePrRull = breddePrRull;
  }

  public String getNavn() {
    return navn;
  }

  public double getPrisPrRull() {
    return prisPrRull;
  }

  public double getLengdePrRull() {
    return lengdePrRull;
  }

  public double getBreddePrRull() {
    return breddePrRull;
  }

  /**
   * Metoden finner totalt antall ruller som trengs for å tapetsere en flate.
   */
  public int beregnAntRuller(Flate flaten) {
    double lengdeFlate = flaten.getLengde();
    double høydeFlate = flaten.getBredde();

    /* finner antall høyder vi trenger: */
    int antHøyder = (int) (lengdeFlate / breddePrRull);
    double rest = lengdeFlate % breddePrRull;
    if (rest >= GRENSE) {
      antHøyder++;
    }

    /* finner antall ruller som trengs */
    int antRuller;
    int antHøyderPrRull = (int) (lengdePrRull / høydeFlate);
    if (antHøyderPrRull > 0) {
      antRuller = antHøyder / antHøyderPrRull;
      rest = antHøyder % antHøyderPrRull;
      if (rest >= GRENSE) {
        antRuller++;
      }
    } else {  // rullen er for kort til å dekke en høyde (sjelden!)
      double totAntMeter = antHøyder * høydeFlate;
      antRuller = (int) (totAntMeter / lengdePrRull);
      if (totAntMeter % lengdePrRull >= GRENSE) antRuller++;
    }
    return antRuller;
  }

  public double beregnTotalpris(Flate flaten) {
    return beregnAntRuller(flaten) * prisPrRull;
  }

  /**
   * To tapettyper defineres som identiske hvis de har samme navn.
   */
  public boolean equals(Object denAndre) {
    if (!(denAndre instanceof Tapet)) {
      return false;  // RETUR. Raskt uthopp hvis feil objekttype.
    }
    if (this == denAndre) {
      return true;  // RETUR. Raskt uthopp hvis samme objekt.
    }
    Tapet tapet = (Tapet) denAndre;
    return (navn.equals(tapet.navn));  // RETUR. Sammenligner navn.
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Tapet: %s, pris kr %.2f pr. rull, lengde rull: %.2f m, bredde rull: %.2f m.",
      navn, prisPrRull, lengdePrRull, breddePrRull);
    return f.toString();
  }
}