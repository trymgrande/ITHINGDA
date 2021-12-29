/**
 * Belegg.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Belegg er en spesialisering av ateriale.
 */
package oppussingsprosjekt;
public  class Belegg extends Materiale {
  /* Grense for å beregne en bredde ekstra. */
  private static final double GRENSE = 0.02;
  private final double bredde;    // meter

  public Belegg(String navn, double pris, double bredde) {
    super(navn, pris);
    if (bredde <= 0.0) {
      throw new IllegalArgumentException(
        "Bredde må være et positive tall. Inndata til konstruktøren var: "+ bredde);
    }
    this.bredde = bredde;
  }

  public double getBredde() {
    return bredde;
  }

  /**
   * Skal beregne antall meter som trengs til en flate.
   * Teppet legges alltid på tvers av golvets lengde.
   * For å finne ut om det er billigere å legge teppet andre veien,
   * må klienten bytte om lengde og bredde i den flaten som
   * sendes inn som argument.
   * Metoden returnerer negativt tall om flaten er null.
   */
  public double beregnMaterialbehov(Flate flaten) {
    if (flaten != null) {
      double lengdeFlate = flaten.getLengde();
      double breddeFlate = flaten.getBredde();
      int antBredder = (int) (lengdeFlate / bredde);
      double rest = lengdeFlate % bredde;
      if (rest >= GRENSE) {
        antBredder++;
      }
      return antBredder * breddeFlate ;
    } else {
      return -1.0;
    }
  }

  public String toString() {
    java.util.Formatter f = new java.util.Formatter();
    f.format("Belegg: %s pr. m, bredde: %.2f m.", super.toString(), bredde);
    return f.toString();
  }
}