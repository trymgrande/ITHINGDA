/**
 * Navneliste.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen innkapsler en lenket liste hvor hvert element inneholder en tekst.
 */

class Navneliste {
  private NavneElement førsteElement;

  /**
   * Metoden returnerer innholdet i listen i tekstlig form.
   */
  public String toString() {
    String listeTekst = "";
    NavneElement hjelpeReferanse = førsteElement;
    while (hjelpeReferanse != null) {
      listeTekst = listeTekst + hjelpeReferanse.getNavn() + "\n";
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    return listeTekst;
  }

  /**
   * Setter inn et navn til slutt i listen.
   */
  public void settInnNavnTilSlutt(String nyttNavn) {
    NavneElement hjelpeReferanse = førsteElement;
    NavneElement hjelpeReferanseRettBak = null;
    /* Vi starter med å bevege oss fram til slutten av listen. */
    while (hjelpeReferanse != null) {
      hjelpeReferanseRettBak = hjelpeReferanse;
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    if (hjelpeReferanseRettBak == null ) {
      /* Dette skjer om listen var tom fra før. */
      førsteElement = new NavneElement(nyttNavn);
    } else {
      /*
       * Dette skjer dersom det var minst ett element i listen fra
       * før. hjelpeReferanseRettBak refererer nå til det siste elementet.
       */
      hjelpeReferanseRettBak.setNeste(new NavneElement(nyttNavn));
    }
  }

  /**
   * Søker etter et navn i listen.
   */
  public boolean søkEtterNavn(String søkeTekst) {
    NavneElement hjelpeReferanse = førsteElement;
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getNavn().equals(søkeTekst)) {
        return true;
      }
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    return false;
  }

  /**
   * Sletter alle forekomster av den gitte teksten fra listen.
   */
  public void slettNavn(String navnSomSkalSlettes) {
    NavneElement hjelpeReferanse = førsteElement;
    NavneElement hjelpeReferanseRettBak = null;

    /* Vi starter med å iterere oss framover i listen. */
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getNavn().equals(navnSomSkalSlettes)) {
        /*
         * Hjelpereferanse refererer nå til et element som skal
         * slettes. Vi må ha spesialbehandling dersom dette er det
         * første elementet i listen.
         */
        if (hjelpeReferanseRettBak != null) {
          hjelpeReferanseRettBak.setNeste(hjelpeReferanse.getNeste());
        } else {
          førsteElement = hjelpeReferanse.getNeste();
        }
        hjelpeReferanse = hjelpeReferanse.getNeste();
      } else {
        hjelpeReferanseRettBak = hjelpeReferanse;
        hjelpeReferanse = hjelpeReferanse.getNeste();
      }
    }
  }
}
