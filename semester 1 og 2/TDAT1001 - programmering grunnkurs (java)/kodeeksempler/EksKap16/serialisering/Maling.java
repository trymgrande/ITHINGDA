/**
 * Maling.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Maling tilbyr metoder for beregning av materialbehov og pris.
 * Attributter: Malingsnavn (identifikator), pris, antall str�k og dekke-evne.
 */

// L�sning oppgave i kap 11
class Maling implements java.io.Serializable {
  private static final double GRENSE = 0.02;  // grense for � kj�pe 0.5 liter maling til
  private final String navn;  // identifiserer malingen
  private final double pris;  // pr.liter
  private final int antStr�k;
  private final double antKvmPrLiter;

  public Maling(String navn, double pris, int antStr�k, double antKvmPrLiter) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Malingsnavn m� v�re oppgitt.");
    }
    if (pris <= 0.0 || antStr�k <= 0 || antKvmPrLiter <= 0.0) {
      throw new IllegalArgumentException(
                "B�de pris, ant. str�k og dekkevne (kvm/l) m� v�re positive tall.\n" +
                "Inndata til konstrukt�ren var: pris = " + pris +
                ", ant. str�k = " + antStr�k + ", dekkevne (kvm/l) = " + antKvmPrLiter);
    }
    this.navn = navn;
    this.antStr�k = antStr�k;
    this.antKvmPrLiter = antKvmPrLiter;
    this.pris = pris;
  }

  public String getNavn() {
    return navn;
  }

  public double getPrisPrLiter() {
    return pris;
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
   */
  public double beregnAntLiter(Flate enFlate) {
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
  }

  /**
   * Metoden regner ut hva malingen til en bestemt flate koster.
   */
  public double beregnTotalpris(Flate enFlate) {
    return beregnAntLiter(enFlate) * pris;
  }

  /**
   * To objekter er like dersom navnet er det samme.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Maling)) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    Maling m = (Maling) obj;
    return (navn.equals(m.getNavn()));
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Maling: %s, pris kr  %.2f  pr. liter, ant. str�k: %d, ant. kvm/l %.2f",
                 navn, pris, antStr�k, antKvmPrLiter);
    return f.toString();
  }
}