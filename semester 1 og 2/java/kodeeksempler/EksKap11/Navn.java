/**
 * Navn.java  - "Programmering i Java", 4.utgave - 2009-07-01
 * Enkel navneklasse med to objektvariabler; fornavn og etternavn.
 * Klassen er mutabel. Både fornavn og etternavn kan endres.
 */

class Navn {
  private String fornavn;
  private String etternavn;

  public Navn(String fornavn, String etternavn) {
    this.fornavn = fornavn.trim();
    this.etternavn = etternavn.trim();
  }

  public void setFornavn(String fornavn) {
    this.fornavn = fornavn.trim();
  }

  public void setEtternavn(String etternavn) {
    this.etternavn = etternavn.trim();
  }

  public String getFornavn() {
    return fornavn;
  }

  public String getEtternavn() {
    return etternavn;
  }

  public String toString() {
    return fornavn + " " + etternavn;
  }
}