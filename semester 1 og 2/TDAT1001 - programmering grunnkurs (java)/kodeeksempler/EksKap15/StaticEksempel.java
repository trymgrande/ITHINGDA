/**
 * StaticEksempel.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen Ansatt med static konstant, variabel og metode.
 * Klassen StaticEksempel med eksempel på bruk av klassen Ansatt.
 */

class Ansatt {
  public static final String FIRMA = "Data AS";
  private static int sistBrukteAnsNr = 0;
  private final String navn;
  private final int ansNr;

  public Ansatt(String navn) {
    this.navn = navn;
    sistBrukteAnsNr++;
    ansNr = sistBrukteAnsNr;
  }

  public static int finnSistBrukteAnsNr() {
    return sistBrukteAnsNr;
  }

  public int getAnsNr() {
    return ansNr;
  }

  public String getNavn() {
    return navn;
  }
}

class StaticEksempel {
  public static void main(String[] args) {
    Ansatt ans1 = new Ansatt("Ole Petter Hansen");
    Ansatt ans2 = new Ansatt("Ingrid Jensen");
    Ansatt ans3 = new Ansatt("Berit Nes");
    System.out.println("Ansatte i firmaet: " + Ansatt.FIRMA);
    System.out.println("Navn: " + ans1.getNavn() + ", nr.: " + ans1.getAnsNr());
    System.out.println("Navn: " + ans2.getNavn() + ", nr.: " + ans2.getAnsNr());
    System.out.println("Navn: " + ans3.getNavn() + ", nr.: " + ans3.getAnsNr());
    System.out.println("Sist brukte ansattnr. er " + Ansatt.finnSistBrukteAnsNr());
  }
}

/* Kjøring av programmet:
Ansatte i firmaet: Data AS
Navn: Ole Petter Hansen, nr.: 1
Navn: Ingrid Jensen, nr.: 2
Navn: Berit Nes, nr.: 3
Sist brukte ansattnr. er 3
*/