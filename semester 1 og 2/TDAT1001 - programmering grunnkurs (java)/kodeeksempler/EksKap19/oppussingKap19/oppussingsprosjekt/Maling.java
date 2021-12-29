/**
 * Maling.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Maling er en spesialisering av Materiale.
 */
package oppussingsprosjekt;
public class Maling extends Materiale {
  private static final double GRENSE = 0.02;  // grense for � kj�pe 0.5 liter maling til
  private final int antStr�k;
  private final double antKvmPrLiter;

  public Maling(String navn, double pris, int antStr�k, double antKvmPrLiter) {
    super(navn, pris);
    if (antStr�k <= 0 || antKvmPrLiter <= 0.0) {
      throw new IllegalArgumentException(
                "B�de ant. str�k og dekkevne (kvm/l) m� v�re positive tall.\n"
             + "Inndata til konstrukt�ren var: ant. str�k = "
             + antStr�k + ", dekkevne (kvm/l) = " + antKvmPrLiter);
    }
    this.antStr�k = antStr�k;
    this.antKvmPrLiter = antKvmPrLiter;
  }

  public int getAntStr�k() {
    return antStr�k;
  }

  public double getAntKvmPrLiter() {
    return antKvmPrLiter;
  }

  /**
   * Metoden finner antall liter maling som trengs,
   * behovet rundes oppover til n�rmeste halve liter.
   * Metoden returnerer et negativt tall, dersom enFlate er null.
   */
  public double beregnMaterialbehov(Flate enFlate) {
    if (enFlate != null) {
      double areal = enFlate.beregnAreal();
      double antLiter = areal * antStr�k / antKvmPrLiter;
      int heltAntallLitere = (int) antLiter;
      double overLiteren = antLiter - heltAntallLitere;
      if (overLiteren >= 0.5 + GRENSE) {
        return heltAntallLitere + 1.0;
      } else {
        if (overLiteren >= GRENSE) {
          return heltAntallLitere + 0.5;
        } else {
          return heltAntallLitere;
        }
      }
    } else {
      return -1.0;
    }
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Maling: %s pr. liter, ant. str�k: %d, ant. kvm/l %.2f",
                 super.toString(), antStr�k, antKvmPrLiter);
    return f.toString();
  }
}