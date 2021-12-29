/**
 * Ansatt.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen beskriver en ansatt med nr, navn, skatteprosent og timel�nn.
 * Det er mulig � endre skatteprosent og timel�nn.
 * Det foretas ingen kontroll av at den nye skatteprosenten er under grensen.
 * Skattetrekk, netto- og bruttol�nn kan regnes ut.
 */

class Ansatt {
  public static final double MAKS_SK_PROS = 60.0;

  private final int ansattnr;
  private final String navn;
  private double timel�nn;
  private double skatteprosent = MAKS_SK_PROS;

  public Ansatt(int ansattnr, String navn, double timel�nn) {
    this.ansattnr = ansattnr;
    this.navn = navn;
    this.timel�nn = timel�nn;
  }

  public Ansatt(int ansattnr, String navn) {
    this.ansattnr = ansattnr;
    this.navn = navn;
    this.timel�nn = 0.0;
  }

  public int getAnsattnr() {
    return ansattnr;
  }

  public String getNavn() {
    return navn;
  }

  public double getTimel�nn() {
    return timel�nn;
  }

  public double getSkatteprosent() {
    return skatteprosent;
  }

  public void setTimel�nn(double nyTimel�nn) {
      timel�nn = nyTimel�nn;
  }

  public void setSkatteprosent(double nySkattepros) {
      skatteprosent = nySkattepros;
  }

  public double beregnBruttol�nn(double antTimer) {
    return antTimer * timel�nn;
  }

  public double beregnSkattetrekk(double antTimer) {
    return beregnBruttol�nn(antTimer) * skatteprosent / 100.0;
  }

  public double beregnNettol�nn(double antTimer) {
    return beregnBruttol�nn(antTimer) - beregnSkattetrekk(antTimer);
  }

  public String toString() {
    return "Ansattnr.: " + ansattnr + ", navn: " + navn + ", timel�nn: "
            + ", skatteprosent: " + skatteprosent;
  }
}