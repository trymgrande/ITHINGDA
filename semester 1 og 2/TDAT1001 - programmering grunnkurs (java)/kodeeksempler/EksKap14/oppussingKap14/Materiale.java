/**
 * Materiale.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Felles superklasse for ulike typer materialer til bruk ved oppussing.
 */

abstract class Materiale {
  private final String navn;  // identifiserer materialet
  private final double pris;  // pris pr. enhet

  /**
   * Konstruktøren kaster unntaksobjekt om ugyldige argumentverdier.
   */
  public Materiale(String navn, double pris) {
    if (navn == null || navn.trim().equals("")) {
      throw new IllegalArgumentException("Navn på materiale må være oppgitt.");
    }
    if (pris <= 0.0) {
      throw new IllegalArgumentException(
                "Pris må være et positivt tall. Oppgitt pris var " + pris);
    }
    this.navn = navn;
    this.pris = pris;
  }

  public String getNavn() {
    return navn;
  }

  public double getPris() {
    return pris;
  }

  /**
   * Beregner hva det koster å dekke en flate med det aktuelle materialet.
   * Metoden returnerer negativ verdi hvis enFlate er null.
   */
  public double beregnTotalpris(Flate enFlate) {
    return (enFlate == null) ? -1.0 : (beregnMaterialbehov(enFlate) * pris);
  }

  public abstract double beregnMaterialbehov(Flate enFlate);

  /**
   * To materialer defineres som identiske hvis de har samme navn.
   */
  public boolean equals(Object detAndre) {
    if (!(detAndre instanceof Materiale)) {
      return false;  // RETUR. Raskt uthopp hvis feil objekttype.
    }
    if (this == detAndre) {
      return true;  // RETUR. Raskt uthopp hvis samme objekt.
    }
    Materiale belegg = (Materiale) detAndre;
    return (navn.equals(belegg.navn));  // RETUR. Sammenligner navn.
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Materiale: %s, pris: %.2f", navn, pris);
    return f.toString();
  }
}