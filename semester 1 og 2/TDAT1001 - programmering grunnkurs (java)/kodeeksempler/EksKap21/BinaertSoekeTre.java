/**
 * BinaertSoekeTre.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasse for et bin�rt s�ketre
 *
 */
class BinaertSoekeTre {
  private SubTre rot;
  public String toString() {
    if (rot != null) {
      return rot.toString();
    } else {
      return null;
    }
  }

  /**
   * Setter inn ny verdi i treet. Da m� vi sjekke om det er
   * noe i treet fra f�r. Det er ikke s� elegant.
   */
  public void settInnVerdi(int verdi) {
    if (rot != null) {
      rot.settInnVerdi(verdi);
    } else {
      rot = new SubTre(verdi, null);
    }
  }

  /**
   * S�ker etter en verdi i treet.
   * Returnerer true dersom verdien finnes i treet, ellers false.
   */
  public boolean s�kEtterVerdi(int s�keVerdi) {
    if (rot == null) {
      return false;
    }
    return rot.s�kEtterVerdi(s�keVerdi);
  }
}