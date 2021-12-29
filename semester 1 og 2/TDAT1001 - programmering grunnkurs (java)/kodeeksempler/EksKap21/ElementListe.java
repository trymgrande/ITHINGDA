/**
 * ElementListe.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen innkapsler en lenket liste av elementer av en vilk�rlig klassetype.
 * Metodene toString() og equals() m� v�re definert for klassen,
 * hvis ikke blir arvede utgaver av disse metodene brukt.
 */

class ElementListe<Type> {
  private ListeElement<Type> f�rsteElement;

  /**
   * Metoden returnerer innholdet i listen i tekstlig form.
   * Bruker toString() for klassen Type.
   */
  public String toString() {
    String listeTekst = "";
    ListeElement<Type> hjelpeReferanse = f�rsteElement;
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
    ListeElement<Type> hjelpeReferanse = f�rsteElement;
    ListeElement<Type> hjelpeReferanseRettBak = null;
    /* Vi starter med � bevege oss fram til slutten av listen. */
    while (hjelpeReferanse != null) {
      hjelpeReferanseRettBak = hjelpeReferanse;
      hjelpeReferanse = hjelpeReferanse.getNeste();
    }
    if (hjelpeReferanseRettBak == null ) {
      /* Dette skjer om listen var tom fra f�r. */
      f�rsteElement = new ListeElement<Type>(nyttElement);
    } else {
      /*
       * Dette skjer dersom det var minst ett element i listen fra
       * f�r. hjelpeReferanseRettBak refererer n� til det siste
       * elementet.
       */
      hjelpeReferanseRettBak.setNeste(new ListeElement<Type>(nyttElement));
    }
  }

  /**
   * S�ker etter et element i listen. Bruker equals() til sammenligning.
   */
  public boolean s�kEtterElement(Type s�keElement) {
    ListeElement<Type> hjelpeReferanse = f�rsteElement;
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getElement().equals(s�keElement)) {
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
    ListeElement<Type> hjelpeReferanse = f�rsteElement;
    ListeElement<Type> hjelpeReferanseRettBak = null;
    /* Vi starter med � iterere oss framover i listen. */
    while (hjelpeReferanse != null) {
      if (hjelpeReferanse.getElement().equals(elementSomSkalSlettes)) {
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