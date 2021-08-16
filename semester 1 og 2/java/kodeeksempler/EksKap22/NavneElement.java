/**
 * NavneElement.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen skal være et element i en enkeltlenket liste som inneholder
 * noen navn.
 */

class NavneElement {
  private NavneElement neste;
  private String navn;

  public NavneElement(String navn) {
    this.navn = navn;
  }

  public NavneElement(String navn, NavneElement neste) {
    this.navn = navn;
    this.neste = neste;
  }

  public String getNavn() {
    return navn;
  }

  public NavneElement getNeste() {
    return neste;
  }

  public void setNavn(String navn) {
    this.navn = navn;
  }

  public void setNeste(NavneElement neste) {
    this.neste = neste;
  }
}
