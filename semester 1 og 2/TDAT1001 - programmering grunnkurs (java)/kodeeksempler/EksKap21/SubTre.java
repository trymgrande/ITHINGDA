/**
 * SubTre.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasse for delene av et binært søketre. Subtrærne, inklusive
 * bladene, består av nye objekter av klassen.
 */

class SubTre {
  private SubTre høyreTre = null;
  private SubTre venstreTre = null;
  private SubTre forelder = null;
  private int verdi = 0;

  public SubTre(int verdi) {
    this.verdi = verdi;
  }

  public SubTre(int verdi, SubTre forelder) {
    this.verdi = verdi;
    this.forelder = forelder;
  }

  /**
   * Metoden setter inn en ny verdi i dette (sub)treet.
   */
  public void settInnVerdi(int nyVerdi) {
    /*
     * Dersom verdien for dette hjørnet er større enn eller likden
     * nye, skal den nye verdien inn i venstre subtre.
     */
    if (verdi >= nyVerdi) {
      if (venstreTre != null) {
        venstreTre.settInnVerdi(nyVerdi);
      } else {
        venstreTre = new SubTre(nyVerdi, this);
      }
    } else {
      if (høyreTre != null) {
        høyreTre.settInnVerdi(nyVerdi);
      } else {
        høyreTre = new SubTre(nyVerdi, this);
      }
    }
  }

  /**
   * Metoden traverserer treet infiks og returnerer en tekst med
   * innholdet, separert med blanktegn.
   */
  public String toString () {
    String returStreng = "";
    if (venstreTre != null) {
      returStreng = venstreTre.toString() + " ";
    }
    returStreng = returStreng + verdi;
    if (høyreTre != null) {
      returStreng = returStreng + " " + høyreTre.toString();
    }
    return returStreng;
  }

  /**
   * Metoden returnerer true dersom den oppgitte verdien finnes i treet.
   */
  public boolean søkEtterVerdi(int søkeVerdi) {
    if (søkeVerdi == verdi) {
      return true;
    }
    if (verdi > søkeVerdi) {
      if (venstreTre != null) {
        return venstreTre.søkEtterVerdi(søkeVerdi);
      } else {
        return false;
      }
    } else {
      if (høyreTre != null) {
        return høyreTre.søkEtterVerdi(søkeVerdi);
      } else {
        return false;
      }
    }
  }
}