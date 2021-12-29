/**
 * KnappeInfo.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Informasjon som knyttes til knappene i knapperaden i OppussingKap19GUI.
 * Velger å bruke kombinasjon av store og små bokstaver i navnene på
 * konstantobjektene på grunn av at disse navnene brukes i brukergrensesnittet.
 * (Normalt skal slike navn skrives med bare store bokstaver.)
 */
enum KnappeInfo {
  Flate("Registrer ny flate (golv eller vegg)", 'F', "NyFlate.gif"),
  Maling("Registrer ny malingstype", 'M', "NyMaling.gif"),
  Belegg("Registrer ny type belegg", 'B', "NyttBelegg.gif"),
  Tapet("Registrer ny type tapet", 'T', "NyTapet.gif"),
  Kombiner("Kombiner flate og materiale", 'K', "Kombiner.gif"),
  Avslutt("Avslutt programmet", 'A', "Avslutt.gif");

  private String beskrivelse;  // tooltip
  private String ikonfil;
  private char mnemonic;

  private KnappeInfo(String beskrivelse, char mnemonic, String ikonfil) {
    this.beskrivelse = beskrivelse;
    this.mnemonic = mnemonic;
    this.ikonfil = ikonfil;
  }

  public String getBeskrivelse() {
    return beskrivelse;
  }

  public String getIkonfil() {
    return ikonfil;
  }

  public char getMnemonic() {
    return mnemonic;
  }
}