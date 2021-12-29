/**
 * Ansatt.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen beskriver en ansatt med nr, navn, skatteprosent og timelønn.
 * Det er mulig å endre skatteprosent og timelønn.
 * Det foretas ingen kontroll av at den nye skatteprosenten er under grensen.
 * Skattetrekk, netto- og bruttolønn kan regnes ut.
 */

class Ansatt {
  public static final double MAKS_SK_PROS = 60.0;

  private final int ansattnr;
  private final String navn;
  private double timelønn;
  private double skatteprosent = MAKS_SK_PROS;

  public Ansatt(int ansattnr, String navn, double timelønn) {
    this.ansattnr = ansattnr;
    this.navn = navn;
    this.timelønn = timelønn;
  }

  public Ansatt(int ansattnr, String navn) {
    this.ansattnr = ansattnr;
    this.navn = navn;
    this.timelønn = 0.0;
  }

  public int getAnsattnr() {
    return ansattnr;
  }

  public String getNavn() {
    return navn;
  }

  public double getTimelønn() {
    return timelønn;
  }

  public double getSkatteprosent() {
    return skatteprosent;
  }

  public void setTimelønn(double nyTimelønn) {
      timelønn = nyTimelønn;
  }

  public void setSkatteprosent(double nySkattepros) {
      skatteprosent = nySkattepros;
  }

  public double beregnBruttolønn(double antTimer) {
    return antTimer * timelønn;
  }

  public double beregnSkattetrekk(double antTimer) {
    return beregnBruttolønn(antTimer) * skatteprosent / 100.0;
  }

  public double beregnNettolønn(double antTimer) {
    return beregnBruttolønn(antTimer) - beregnSkattetrekk(antTimer);
  }

  public String toString() {
    return "Ansattnr.: " + ansattnr + ", navn: " + navn + ", timelønn: "
            + ", skatteprosent: " + skatteprosent;
  }
}