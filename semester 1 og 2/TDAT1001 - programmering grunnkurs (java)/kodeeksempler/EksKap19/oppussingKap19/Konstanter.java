/**
 * Konstanter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En del konstanter som benyttes i de �vrige klassene.
 * Se ogs� enum-typer p� filene KnappeInfo.java og Hjelpemeny.java
 */
class Konstanter {
  public static final String IKONKATALOG = "ikoner" + java.io.File.separator;
  public static final String[] KOL_NAVN = {"Flate", "Bredde", "Lengde", "Materiale",
                                                             "Antall enheter", "Pris pr. stk.", "Totalpris"};
  public static final int VINDU_X = 200;  // Plassering av prim�rvinduet, ...
  public static final int VINDU_Y = 300;  // ...., �verste venstre hj�rne
  public static final int DIALOG_X = 300; // Plassering av dialogene, ...
  public static final int DIALOG_Y = 400; // ...., �verste venstre hj�rne
  public static final int TEKSTLENGDE = 15;  // i dialogene
  public static final int
        TEKSTLENGDE_SUM = 5; // i summefeltet nederst i hovedvinduet
  public static final java.awt.Dimension
        STR_FLATELISTEVINDU = new java.awt.Dimension(600, 400);
  /* Format for utskrift med printf() i datamodellklassene */
  public static final String FORMAT = "%8.2f";
}
