/**
 * Tapet.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Tapet tilbyr metoder for beregning av materialbehov og pris.
 *
 */
class Tapet {
  private static final double GRENSE = 0.02; // grense for � beregne en rull ekstra
  private String navn;   // identifiserer tapeten
  private double prisPrRull;   // pris pr. rull
  private double lengdePrRull;    // meter
  private double breddePrRull;    // meter

  /**
   * Konstrukt�ren kaster IllegalArgumentException hvis ugyldige argumenter.
   */
  public Tapet(String navn, double prisPrRull, double lengdePrRull, double breddePrRull) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Beleggets navn m� v�re oppgitt.");
    }
    if (prisPrRull <= 0.0 || lengdePrRull <= 0.0 || breddePrRull <= 0.0) {
      throw new IllegalArgumentException(
                "B�de pris, bredde og lengde m� v�re positive tall.\n" +
                "Inndata til konstrukt�ren var: pris = " + prisPrRull +
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
   * Metoden finner totalt antall ruller som trengs for � tapetsere en flate.
   */
  public int beregnAntRuller(Flate flaten) {
    double lengdeFlate = flaten.getLengde();
    double h�ydeFlate = flaten.getBredde();

    /* finner antall h�yder vi trenger: */
    int antH�yder = (int) (lengdeFlate / breddePrRull);
    double rest = lengdeFlate % breddePrRull;
    if (rest >= GRENSE) {
      antH�yder++;
    }

    /* finner antall ruller som trengs */
    int antRuller;
    int antH�yderPrRull = (int) (lengdePrRull / h�ydeFlate);
    if (antH�yderPrRull > 0) {
      antRuller = antH�yder / antH�yderPrRull;
      rest = antH�yder % antH�yderPrRull;
      if (rest >= GRENSE) {
        antRuller++;
      }
    } else {  // rullen er for kort til � dekke en h�yde (sjelden!)
      double totAntMeter = antH�yder * h�ydeFlate;
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