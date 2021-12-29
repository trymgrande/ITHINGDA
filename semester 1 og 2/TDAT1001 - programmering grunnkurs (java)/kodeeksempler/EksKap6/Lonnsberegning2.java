/**
 * Lonnsberegning2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Finner nettolønnen for ulike skatteprosenter for én ansatt.
 *
 * Filen inneholder to klasser:
 * LonnsBGS: Klassen sørger for all kommunikasjon på detaljnivå mellom brukeren
 * og et ansattobjekt for en helt spesiell anvendelse.
 * Lonnsberegning2: Inneholder main(), som viser hovedflyten i programmet.
 */

import static javax.swing.JOptionPane.*;

class Lonnsberegning2 {
  public static void main(String[] args) {
    final double ANT_TIMER = 20.0;
    Ansatt ansatt = new Ansatt(12345, "Anne Vik");
    LonnsBGS bgs = new LonnsBGS(ansatt);
    String timelønnLest = bgs.lesTimelønnSomTekst();
    while (timelønnLest != null) {
      bgs.registrerNyTimelønn(timelønnLest);
      bgs.registrerNySkattepros();
      double nettolønn = ansatt.beregnNettolønn(ANT_TIMER);
      bgs.skrivUtNettolønn(nettolønn);
      timelønnLest = bgs.lesTimelønnSomTekst();
    }
  }
}

class LonnsBGS {
  private final Ansatt enAnsatt;

  public LonnsBGS(Ansatt ansatt) {
    this.enAnsatt = ansatt;
  }

  public String lesTimelønnSomTekst() {
    return showInputDialog("Oppgi timelønn, avslutt med Esc: ");
  }

  public void registrerNyTimelønn(String lønn) {
    double timelønn = Double.parseDouble(lønn);
    enAnsatt.setTimelønn(timelønn);
  }

  public void registrerNySkattepros() {
    double maks = Ansatt.MAKS_SK_PROS;
    double nyPros = lesDesimaltall("Ny skatteprosent, maks " + maks + ": ");
    while (nyPros < 0.0 || nyPros > maks) {
      String melding = "Du skrev: " + nyPros + ", det er ikke gyldig skatteprosent.";
      melding += "\nSkriv et tall i intervallet 0,0 - " + maks+ ": ";
      nyPros = lesDesimaltall(melding);
    }
    enAnsatt.setSkatteprosent(nyPros);
  }

  public void skrivUtNettolønn(double netto) {
    java.util.Formatter formaterer =  new java.util.Formatter(); // formaterer utskriften
    formaterer.format("%.2f", netto);
    String utskrift = "Nettolønn kr " + formaterer.toString();
    showMessageDialog(null, utskrift);
  }

  /*
   * Hjelpemetode på lavere nivå
   */
  private double lesDesimaltall(String ledetekst) {
    String tallLest = showInputDialog(ledetekst);
    return Double.parseDouble(tallLest);
  }
}

/* Kjøring av programmet:
Oppgi timelønn, avslutt med Esc: 100
Ny skatteprosent, maks 60.0: 50
Nettolønn, kr 1000,00
Ny timelønn: 300
Ny skatteprosent, maks 60.0: 10
Nettolønn, kr 5400,00
*/