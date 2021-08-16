/**
 * BinaertSoekeTre.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasse for et binært søketre
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
   * Setter inn ny verdi i treet. Da må vi sjekke om det er
   * noe i treet fra før. Det er ikke så elegant.
   */
  public void settInnVerdi(int verdi) {
    if (rot != null) {
      rot.settInnVerdi(verdi);
    } else {
      rot = new SubTre(verdi, null);
    }
  }

  /**
   * Søker etter en verdi i treet.
   * Returnerer true dersom verdien finnes i treet, ellers false.
   */
  public boolean søkEtterVerdi(int søkeVerdi) {
    if (rot == null) {
      return false;
    }
    return rot.søkEtterVerdi(søkeVerdi);
  }
}