/**
 * Konstanter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En del konstanter som benyttes i de øvrige klassene.
 * Se også enum-typer på filene KnappeInfo.java og Hjelpemeny.java
 */
class Konstanter {
  public static final String IKONKATALOG = "ikoner" + java.io.File.separator;
  public static final String[] KOL_NAVN = {"Flate", "Bredde", "Lengde", "Materiale",
                                                             "Antall enheter", "Pris pr. stk.", "Totalpris"};
  public static final int VINDU_X = 200;  // Plassering av primærvinduet, ...
  public static final int VINDU_Y = 300;  // ...., øverste venstre hjørne
  public static final int DIALOG_X = 300; // Plassering av dialogene, ...
  public static final int DIALOG_Y = 400; // ...., øverste venstre hjørne
  public static final int TEKSTLENGDE = 15;  // i dialogene
  public static final int
        TEKSTLENGDE_SUM = 5; // i summefeltet nederst i hovedvinduet
  public static final java.awt.Dimension
        STR_FLATELISTEVINDU = new java.awt.Dimension(600, 400);
  /* Format for utskrift med printf() i datamodellklassene */
  public static final String FORMAT = "%8.2f";
}
