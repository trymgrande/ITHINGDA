/**
 * Hjelpemeny.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Beskriver menyen oppe til høyre i OppussingKap19GUI.
 */
enum Hjelpemeny {
  VALG_HJELP("Hjelp", 'H',
            "Dette er et program som beregner materialbehov og priser\n"
          + "for oppussing av mange flater med forskjellige materialtyper.\n"
          + "Flater og materialer registreres hver for seg.\n"
          + "Bruk knapperaden eller menyen.\n"
          + "En hvilken som helst flate kan kombineres med et hvilket som\n"
          + "helst materiale. Oversikt over materialbehov og kostnader for\n"
          + "hver enkelt flate og også totalsummen for hele prosjektet\n"
          + "oppdateres kontinuerlig.\n"
          + "Desimalskille (komma eller punktum) følger oppsettet på maskinen."),
  VALG_OM_PROG("Om programmet ...", 'O',
              "Dette programmet er en utgave av et gjennomgående eksempel\n"
          + "i læreboka \"Programmering i Java\", skrevet av\n"
          + "Else Lervik og Vegard Havdal, TISIP, Trondheim.\n"
          + "Boka er et samarbeidsprosjekt mellom TISIP og Gyldendal Akademisk.");

  public static final String MENYNAVN = "Hjelp";
  public static final char MENYNAVN_MNEMONIC = 'H';  // tastaturalternativ

  private String tekst;
  private char mnemonic;
  private String innhold;

  private Hjelpemeny(String tekst, char mnemonic, String innhold) {
    this.tekst = tekst;
    this.mnemonic = mnemonic;
    this.innhold = innhold;
  }

  public String toString() {
    return tekst;
  }

  public char getMnemonic() {
    return mnemonic;
  }

  public String getInnhold() {
    return innhold;
  }

  public static Hjelpemeny finnValg(String tekst) {
    for (Hjelpemeny valg : Hjelpemeny.values()) {
      if (valg.toString().equals(tekst)) {
        return valg;
      }
    }
    return null; // skal ikke komme hit
  }
}