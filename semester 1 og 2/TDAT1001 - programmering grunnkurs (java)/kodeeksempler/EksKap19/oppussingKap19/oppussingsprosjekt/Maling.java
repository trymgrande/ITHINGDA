/**
 * Maling.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Maling er en spesialisering av Materiale.
 */
package oppussingsprosjekt;
public class Maling extends Materiale {
  private static final double GRENSE = 0.02;  // grense for å kjøpe 0.5 liter maling til
  private final int antStrøk;
  private final double antKvmPrLiter;

  public Maling(String navn, double pris, int antStrøk, double antKvmPrLiter) {
    super(navn, pris);
    if (antStrøk <= 0 || antKvmPrLiter <= 0.0) {
      throw new IllegalArgumentException(
                "Både ant. strøk og dekkevne (kvm/l) må være positive tall.\n"
             + "Inndata til konstruktøren var: ant. strøk = "
             + antStrøk + ", dekkevne (kvm/l) = " + antKvmPrLiter);
    }
    this.antStrøk = antStrøk;
    this.antKvmPrLiter = antKvmPrLiter;
  }

  public int getAntStrøk() {
    return antStrøk;
  }

  public double getAntKvmPrLiter() {
    return antKvmPrLiter;
  }

  /**
   * Metoden finner antall liter maling som trengs,
   * behovet rundes oppover til nærmeste halve liter.
   * Metoden returnerer et negativt tall, dersom enFlate er null.
   */
  public double beregnMaterialbehov(Flate enFlate) {
    if (enFlate != null) {
      double areal = enFlate.beregnAreal();
      double antLiter = areal * antStrøk / antKvmPrLiter;
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
    f.format("Maling: %s pr. liter, ant. strøk: %d, ant. kvm/l %.2f",
                 super.toString(), antStrøk, antKvmPrLiter);
    return f.toString();
  }
}