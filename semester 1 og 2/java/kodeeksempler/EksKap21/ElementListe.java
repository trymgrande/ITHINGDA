/**
 * ElementListe.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen innkapsler en lenket liste av elementer av en vilkårlig klassetype.
 * Metodene toString() og equals() må være definert for klassen,
 * hvis ikke blir arvede utgaver av disse metodene brukt.
 */

class ElementListe<Type> {
  private ListeElement<Type> førsteElement;

  /**
   * Metoden returnerer innholdet i listen i tekstlig form.
   * Bruker toString() for klassen Type.
   */
  public String toString() {
    String listeTekst = "";
    ListeElement<Type> hjelpeReferanse = førsteElement;
    while (hjelpeReferanse != null) {
      listeTekst += hjelpeReferanse.getElement().toString() + "\n";
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    return listeTekst;
  }

  /**
   * Setter inn et element til slutt i listen.
   */
  public void settInnElementTilSlutt(Type nyttElement) {
    ListeElement<Type> hjelpeReferanse = førsteElement;
    ListeElement<Type> hjelpeReferanseRettBak = null;
    /* Vi starter med å bevege oss fram til slutten av listen. */
    while (hjelpeReferanse != null) {
      hjelpeReferanseRettBak = hjelpeReferanse;
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    if (hjelpeReferanseRettBak == null ) {
      /* Dette skjer om listen var tom fra før. */
      førsteElement = new ListeElement<Type>(nyttElement);
    } else {
      /*
       * Dette skjer dersom det var minst ett element i listen fra
       * før. hjelpeReferanseRettBak refererer nå til det siste
       * elementet.
       */
      hjelpeReferanseRettBak.setNeste(new ListeElement<Type>(nyttElement));
    }
  }

  /**
   * Søker etter et element i listen. Bruker equals() til sammenligning.
   */
  public boolean søkEtterElement(Type søkeElement) {
    ListeElement<Type> hjelpeReferanse = førsteElement;
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getElement().equals(søkeElement)) {
        return true;
      }
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    return false;
  }

  /**
   * Sletter alle forekomster av elementet fra listen.
   * Bruker equals() for sammenligning.
   */
  public void slettElement(Type elementSomSkalSlettes) {
    ListeElement<Type> hjelpeReferanse = førsteElement;
    ListeElement<Type> hjelpeReferanseRettBak = null;
    /* Vi starter med å iterere oss framover i listen. */
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getElement().equals(elementSomSkalSlettes)) {
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