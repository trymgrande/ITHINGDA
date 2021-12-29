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
   * Det lages en ny liste f�r hver testmetode kj�res.
   */
  @Before
  public void f�rTest() {
    testListe = new Navneliste();
  }

  /**
   * Etter hver testmetode setter vi listeobjektet til null. Det er
   * un�dvendig fordi vi lager et nytt i setUp(), men det gj�r koden noe klarere
   * med tanke p� at vi vil jobbe med et helt nytt objekt hver gang.
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
  public void testS�k() {
    testListe.settInnNavnTilSlutt("Linda");
    testListe.settInnNavnTilSlutt("Trine");
    testListe.settInnNavnTilSlutt("Kari");
    testListe.settInnNavnTilSlutt("Sara");
    testListe.settInnNavnTilSlutt("Wenche");
    testListe.settInnNavnTilSlutt("Hege");
    testListe.settInnNavnTilSlutt("Lise");
    /* Vi verifiserer at samtlige navn er med: */
    assertTrue(testListe.s�kEtterNavn("Linda"));
    assertTrue(testListe.s�kEtterNavn("Trine"));
    assertTrue(testListe.s�kEtterNavn("Kari"));
    assertTrue(testListe.s�kEtterNavn("Sara"));
    assertTrue(testListe.s�kEtterNavn("Wenche"));
    assertTrue(testListe.s�kEtterNavn("Hege"));
    assertTrue(testListe.s�kEtterNavn("Lise"));
    /* S� sjekker vi noen strenger som ikke skal v�re i listen: */
    assertFalse(testListe.s�kEtterNavn(null));
    assertFalse(testListe.s�kEtterNavn(""));
    assertFalse(testListe.s�kEtterNavn("lise"));
    assertFalse(testListe.s�kEtterNavn("hege"));
    assertFalse(testListe.s�kEtterNavn("LindaTrine"));
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
