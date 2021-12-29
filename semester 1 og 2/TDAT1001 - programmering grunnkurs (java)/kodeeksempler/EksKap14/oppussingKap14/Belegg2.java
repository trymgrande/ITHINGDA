/**
 * Belegg2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasser for å illustrere arv i to nivåer.
 */

/*
 * Vi lager denne klassen abstrakt. Den har imidlertid ingen abstrakte metoder.
 */
class Belegg2 extends Materiale {
  /* Grense for å beregne en bredde ekstra. */
  private static final double GRENSE = 0.02;
  private final double bredde;    // meter

  public Belegg2(String navn, double pris, double bredde) {
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
    f.format("%s, bredde: %.2f m", super.toString(), bredde);
    return f.toString();
  }
}

/**
 * Belegg av førstesortering. Ingen endringer i forhold til klassen Belegg2.
 */
final class ForsteSortBelegg extends Belegg2 {

  public ForsteSortBelegg(String navn, double pris, int bredde) {
    super(navn, pris, bredde);
  }
}

/**
 * Belegg av annensortering. Reviderte beregningsalgoritmer for behov og pris.
 */
final class AnnenSortBelegg extends Belegg2 {
  public static final double GRENSE_HØYRABATT = 5;
  public static final double HØYESTE_RABATT = 0.7;
  public static final double LAVESTE_RABATT = 0.5;
  public static final double MATERIALTILLEGG = 1.2; // faktor for materialtillegg

  public AnnenSortBelegg(String navn, double pris, int bredde) {
    super(navn, pris, bredde);
  }

  public double beregnTotalpris(Flate enFlate) {
    double grunnpris = super.beregnTotalpris(enFlate);
    double behov = beregnMaterialbehov(enFlate);
    if (behov > GRENSE_HØYRABATT) {
      return grunnpris * (1.0 - HØYESTE_RABATT);
    } else {
      return grunnpris * (1.0 - LAVESTE_RABATT);
    }
  }

  public double beregnMaterialbehov(Flate enFlate) {
    double grunnBehov = super.beregnMaterialbehov(enFlate);
    return grunnBehov * MATERIALTILLEGG;
  }
}