/**
 * Lonnsberegning2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Finner nettol�nnen for ulike skatteprosenter for �n ansatt.
 *
 * Filen inneholder to klasser:
 * LonnsBGS: Klassen s�rger for all kommunikasjon p� detaljniv� mellom brukeren
 * og et ansattobjekt for en helt spesiell anvendelse.
 * Lonnsberegning2: Inneholder main(), som viser hovedflyten i programmet.
 */

import static javax.swing.JOptionPane.*;

class Lonnsberegning2 {
  public static void main(String[] args) {
    final double ANT_TIMER = 20.0;
    Ansatt ansatt = new Ansatt(12345, "Anne Vik");
    LonnsBGS bgs = new LonnsBGS(ansatt);
    String timel�nnLest = bgs.lesTimel�nnSomTekst();
    while (timel�nnLest != null) {
      bgs.registrerNyTimel�nn(timel�nnLest);
      bgs.registrerNySkattepros();
      double nettol�nn = ansatt.beregnNettol�nn(ANT_TIMER);
      bgs.skrivUtNettol�nn(nettol�nn);
      timel�nnLest = bgs.lesTimel�nnSomTekst();
    }
  }
}

class LonnsBGS {
  private final Ansatt enAnsatt;

  public LonnsBGS(Ansatt ansatt) {
    this.enAnsatt = ansatt;
  }

  public String lesTimel�nnSomTekst() {
    return showInputDialog("Oppgi timel�nn, avslutt med Esc: ");
  }

  public void registrerNyTimel�nn(String l�nn) {
    double timel�nn = Double.parseDouble(l�nn);
    enAnsatt.setTimel�nn(timel�nn);
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

  public void skrivUtNettol�nn(double netto) {
    java.util.Formatter formaterer =  new java.util.Formatter(); // formaterer utskriften
    formaterer.format("%.2f", netto);
    String utskrift = "Nettol�nn kr " + formaterer.toString();
    showMessageDialog(null, utskrift);
  }

  /*
   * Hjelpemetode p� lavere niv�
   */
  private double lesDesimaltall(String ledetekst) {
    String tallLest = showInputDialog(ledetekst);
    return Double.parseDouble(tallLest);
  }
}

/* Kj�ring av programmet:
Oppgi timel�nn, avslutt med Esc: 100
Ny skatteprosent, maks 60.0: 50
Nettol�nn, kr 1000,00
Ny timel�nn: 300
Ny skatteprosent, maks 60.0: 10
Nettol�nn, kr 5400,00
*/