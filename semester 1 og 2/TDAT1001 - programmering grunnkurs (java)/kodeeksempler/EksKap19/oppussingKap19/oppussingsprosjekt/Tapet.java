/**
 * Tapet.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Tapet tilbyr er en spesialisering av materiale.
 *
 */
package oppussingsprosjekt;
public class Tapet extends Materiale {
  private static final double GRENSE = 0.02; // grense for � beregne en rull ekstra
  private final double lengdePrRull;    // meter
  private final double breddePrRull;    // meter

  public Tapet(String navn, double pris, double lengdePrRull, double breddePrRull) {
    super(navn, pris);
    if (lengdePrRull <= 0.0 || breddePrRull <= 0.0) {
      throw new IllegalArgumentException(
                "B�de bredde og lengde m� v�re positive tall.\n" +
                "Inndata til konstrukt�ren var: lengde = " +
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
   * Metoden finner totalt antall ruller som trengs for � tapetsere en flate.
   * Metoden returnerer et negativt tall dersom flaten er null.
   */
  public double beregnMaterialbehov(Flate flaten) {
    if (flaten != null) {
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