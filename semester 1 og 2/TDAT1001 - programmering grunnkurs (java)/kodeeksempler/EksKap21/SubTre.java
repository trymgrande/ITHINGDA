/**
 * SubTre.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasse for delene av et bin�rt s�ketre. Subtr�rne, inklusive
 * bladene, best�r av nye objekter av klassen.
 */

class SubTre {
  private SubTre h�yreTre = null;
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
     * Dersom verdien for dette hj�rnet er st�rre enn eller likden
     * nye, skal den nye verdien inn i venstre subtre.
     */
    if (verdi >= nyVerdi) {
      if (venstreTre != null) {
        venstreTre.settInnVerdi(nyVerdi);
      } else {
        venstreTre = new SubTre(nyVerdi, this);
      }
    } else {
      if (h�yreTre != null) {
        h�yreTre.settInnVerdi(nyVerdi);
      } else {
        h�yreTre = new SubTre(nyVerdi, this);
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
    if (h�yreTre != null) {
      returStreng = returStreng + " " + h�yreTre.toString();
    }
    return returStreng;
  }

  /**
   * Metoden returnerer true dersom den oppgitte verdien finnes i treet.
   */
  public boolean s�kEtterVerdi(int s�keVerdi) {
    if (s�keVerdi == verdi) {
      return true;
    }
    if (verdi > s�keVerdi) {
      if (venstreTre != null) {
        return venstreTre.s�kEtterVerdi(s�keVerdi);
      } else {
        return false;
      }
    } else {
      if (h�yreTre != null) {
        return h�yreTre.s�kEtterVerdi(s�keVerdi);
      } else {
        return false;
      }
    }
  }
}