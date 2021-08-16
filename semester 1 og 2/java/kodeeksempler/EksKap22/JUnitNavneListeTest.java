/**
 * JUnitNavneListeTest.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * JUnit-test for klassen NavneListe
 *
 */
import org.junit.*;
import static org.junit.Assert.*;

public class JUnitNavneListeTest {
  private Navneliste testListe;

  /**
   * Det lages en ny liste før hver testmetode kjøres.
   */
  @Before
  public void førTest() {
    testListe = new Navneliste();
  }

  /**
   * Etter hver testmetode setter vi listeobjektet til null. Det er
   * unødvendig fordi vi lager et nytt i setUp(), men det gjør koden noe klarere
   * med tanke på at vi vil jobbe med et helt nytt objekt hver gang.
   */
  @After
  public void etteTest() {
    testListe = null;
  }

  @Test
  public void testInnsetting() {
    testListe.settInnNavnTilSlutt("Truls");
    testListe.settInnNavnTilSlutt("Arne");
    testListe.settInnNavnTilSlutt("Sigvart");
    testListe.settInnNavnTilSlutt("Jonas");
    testListe.settInnNavnTilSlutt("Thomas");
    testListe.settInnNavnTilSlutt("Oddgeir");
    testListe.settInnNavnTilSlutt("Erlend");
    String listeTekst = testListe.toString();
    assertTrue(listeTekst.equals
      ("Truls\nArne\nSigvart\nJonas\nThomas\nOddgeir\nErlend\n"));
  }

  @Test
  public void testSøk() {
    testListe.settInnNavnTilSlutt("Linda");
    testListe.settInnNavnTilSlutt("Trine");
    testListe.settInnNavnTilSlutt("Kari");
    testListe.settInnNavnTilSlutt("Sara");
    testListe.settInnNavnTilSlutt("Wenche");
    testListe.settInnNavnTilSlutt("Hege");
    testListe.settInnNavnTilSlutt("Lise");
    /* Vi verifiserer at samtlige navn er med: */
    assertTrue(testListe.søkEtterNavn("Linda"));
    assertTrue(testListe.søkEtterNavn("Trine"));
    assertTrue(testListe.søkEtterNavn("Kari"));
    assertTrue(testListe.søkEtterNavn("Sara"));
    assertTrue(testListe.søkEtterNavn("Wenche"));
    assertTrue(testListe.søkEtterNavn("Hege"));
    assertTrue(testListe.søkEtterNavn("Lise"));
    /* Så sjekker vi noen strenger som ikke skal være i listen: */
    assertFalse(testListe.søkEtterNavn(null));
    assertFalse(testListe.søkEtterNavn(""));
    assertFalse(testListe.søkEtterNavn("lise"));
    assertFalse(testListe.søkEtterNavn("hege"));
    assertFalse(testListe.søkEtterNavn("LindaTrine"));
  }

  @Test
  public void testSletting() {
    testListe.settInnNavnTilSlutt("Truls");
    testListe.settInnNavnTilSlutt("Arne");
    testListe.settInnNavnTilSlutt("Sigvart");
    testListe.settInnNavnTilSlutt("Jonas");
    testListe.settInnNavnTilSlutt("Thomas");
    testListe.settInnNavnTilSlutt("Oddgeir");
    testListe.settInnNavnTilSlutt("Erlend");
    String listeTekst = testListe.toString();
    assertTrue(listeTekst.equals
      ("Truls\nArne\nSigvart\nJonas\nThomas\nOddgeir\nErlend\n"));
    testListe.slettNavn("Thomas");
    testListe.slettNavn("Arne");
    testListe.slettNavn("Truls");
    listeTekst = testListe.toString();
    assertTrue(listeTekst.equals("Sigvart\nJonas\nOddgeir\nErlend\n"));
  }

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(JUnitNavneListeTest.class.getName());
  }
}

/*
Utskrift:
...
Time: 0,03

OK (3 tests)
*/
