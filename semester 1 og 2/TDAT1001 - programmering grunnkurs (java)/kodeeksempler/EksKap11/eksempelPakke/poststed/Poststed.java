/**
 * Poststed.java  - "Programmering i Java", 4.utgave - 2009-07-01
 * Mutabel klasse med to objektvariabler; postnr. og poststed.
 */
package poststed;
public class Poststed {
  private String postnr;
  private String sted;

  public Poststed(String postnr, String sted) {
    this.postnr = postnr;
    this.sted = sted.trim();
  }

  public String getPostnr() {
    return postnr;
  }

  public String getSted() {
    return sted;
  }

  void setPostnr(String postnr) {
    this.postnr = postnr.trim();
  }

  void setSted(String sted) {
    this.sted = sted.trim();
  }

  public String toString() {
    return postnr + " " + sted;
  }
}