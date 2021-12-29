/**
 * Navneliste.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen innkapsler en lenket liste hvor hvert element inneholder en tekst.
 */

class Navneliste {
  private NavneElement f�rsteElement;

  /**
   * Metoden returnerer innholdet i listen i tekstlig form.
   */
  public String toString() {
    String listeTekst = "";
    NavneElement hjelpeReferanse = f�rsteElement;
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
    NavneElement hjelpeReferanse = f�rsteElement;
    NavneElement hjelpeReferanseRettBak = null;
    /* Vi starter med � bevege oss fram til slutten av listen. */
    while (hjelpeReferanse != null) {
      hjelpeReferanseRettBak = hjelpeReferanse;
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    if (hjelpeReferanseRettBak == null ) {
      /* Dette skjer om listen var tom fra f�r. */
      f�rsteElement = new NavneElement(nyttNavn);
    } else {
      /*
       * Dette skjer dersom det var minst ett element i listen fra
       * f�r. hjelpeReferanseRettBak refererer n� til det siste elementet.
       */
      hjelpeReferanseRettBak.setNeste(new NavneElement(nyttNavn));
    }
  }

  /**
   * S�ker etter et navn i listen.
   */
  public boolean s�kEtterNavn(String s�keTekst) {
    NavneElement hjelpeReferanse = f�rsteElement;
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getNavn().equals(s�keTekst)) {
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
    NavneElement hjelpeReferanse = f�rsteElement;
    NavneElement hjelpeReferanseRettBak = null;

    /* Vi starter med � iterere oss framover i listen. */
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getNavn().equals(navnSomSkalSlettes)) {
        /*
         * Hjelpereferanse refererer n� til et element som skal
         * slettes. Vi m� ha spesialbehandling dersom dette er det
         * f�rste elementet i listen.
         */
        if (hjelpeReferanseRettBak != null) {
          hjelpeReferanseRettBak.setNeste(hjelpeReferanse.getNeste());
        } else {
          f�rsteElement = hjelpeReferanse.getNeste();
        }
        hjelpeReferanse = hjelpeReferanse.getNeste();
      } else {
        hjelpeReferanseRettBak = hjelpeReferanse;
        hjelpeReferanse = hjelpeReferanse.getNeste();
      }
    }
  }
}
