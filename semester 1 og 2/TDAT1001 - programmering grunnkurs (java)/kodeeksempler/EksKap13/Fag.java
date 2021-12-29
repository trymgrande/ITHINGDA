/**
 * Fag.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Fag med attributtene fagkode, navn og antall studenter.
 * Antall studenter kan endres.
 */

class Fag {
  private final String fagkode;  // entydig
  private final String fagnavn;
  private int antStud = 0;  // endres med metoden setAntStud()

  public Fag(String fagkode, String fagnavn) {
    if (fagkode == null || fagkode.trim().equals("")
                                   || fagnavn == null || fagkode.trim().equals("")) {
      throw new IllegalArgumentException("Fagkode og fagnavn må oppgis.");
    }
    this.fagkode = fagkode.trim();
    this.fagnavn = fagnavn.trim();
  }

  public String getFagkode() {
    return fagkode;
  }

  public String getFagnavn() {
    return fagnavn;
  }

  public int getAntStud() {
    return antStud;
  }

  public void setAntStud(int nyAntStud) {
    if (nyAntStud < 0) {
      throw new IllegalArgumentException("Antall studenter kan ikke være negativt.");
    }
    antStud = nyAntStud;
  }

  /**
   * To objekter er definert som like dersom de har samme fagkode.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Fag)) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    Fag f = (Fag) obj;
    return (fagkode.equals(f.getFagkode()));
  }

  public String toString() {
    return "Kode: " + fagkode + ", Navn: " + fagnavn + ", " + antStud + " studenter.";
  }
}