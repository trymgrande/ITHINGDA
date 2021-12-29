/**
 * EnkelEnumEksempel.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Ansatt med enum-type som medlem.
 */

class Ansatt {
  public enum Ansettelsesforhold {
    FAST, MIDLERTIDIG, PROSJEKT, SLUTTET
  }

  private final String navn;
  private Ansettelsesforhold status = Ansettelsesforhold.FAST;

  public Ansatt(String navn) {
    this.navn = navn;
  }

  public String getNavn() {
    return navn;
  }

  public Ansettelsesforhold getStatus() {
    return status;
  }

  public void setStatus(Ansettelsesforhold nyStatus) {
    status = nyStatus;
  }
}

class EnkelEnumEksempel {
  public static void main(String[] args) {
    Ansatt ans1 = new Ansatt("Ole Petter Hansen");
    Ansatt ans2 = new Ansatt("Ingrid Jensen");
    Ansatt ans3 = new Ansatt("Berit Nes");
    ans1.setStatus(Ansatt.Ansettelsesforhold.MIDLERTIDIG);
    ans3.setStatus(Ansatt.Ansettelsesforhold.SLUTTET);
    System.out.println("Navn: " + ans1.getNavn() + ", ans. forh.: " + ans1.getStatus());
    System.out.println("Navn: " + ans2.getNavn() + ", ans. forh.: " + ans2.getStatus());
    System.out.println("Navn: " + ans3.getNavn() + ", ans. forh.: " + ans3.getStatus());
  }
}

/* Kjøring av programmet:
Navn: Ole Petter Hansen, ans. forh.: MIDLERTIDIG
Navn: Ingrid Jensen, ans. forh.: FAST
Navn: Berit Nes, ans. forh.: SLUTTET
*/