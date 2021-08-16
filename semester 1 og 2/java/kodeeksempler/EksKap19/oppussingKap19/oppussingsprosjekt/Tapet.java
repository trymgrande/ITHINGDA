/**
 * Tapet.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Tapet tilbyr er en spesialisering av materiale.
 *
 */
package oppussingsprosjekt;
public class Tapet extends Materiale {
  private static final double GRENSE = 0.02; // grense for å beregne en rull ekstra
  private final double lengdePrRull;    // meter
  private final double breddePrRull;    // meter

  public Tapet(String navn, double pris, double lengdePrRull, double breddePrRull) {
    super(navn, pris);
    if (lengdePrRull <= 0.0 || breddePrRull <= 0.0) {
      throw new IllegalArgumentException(
                "Både bredde og lengde må være positive tall.\n" +
                "Inndata til konstruktøren var: lengde = " +
                lengdePrRull + ", bredde = " + breddePrRull);
    }
    this.lengdePrRull = lengdePrRull;
    this.breddePrRull = breddePrRull;
  }

  public double getLengdePrRull() {
    return lengdePrRull;
  }

  public double getBreddePrRull() {
    return breddePrRull;
  }

  /**
   * Metoden finner totalt antall ruller som trengs for å tapetsere en flate.
   * Metoden returnerer et negativt tall dersom flaten er null.
   */
  public double beregnMaterialbehov(Flate flaten) {
    if (flaten != null) {
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
    } else {
      return -1.0;
    }
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Tapet: %s pr. rull, lengde rull: %.2f m, bredde rull: %.2f m.",
      super.toString(), lengdePrRull, breddePrRull);
    return f.toString();
  }
}