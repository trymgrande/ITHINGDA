/**
 * MineTall.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder to klasser:
 * Tallrekke: Klasse for lagring av en tallrekke med vilkårlig størrelse.
 * Ett og ett tall kan legges inn av gangen. toString()-metode for å
 * hente ut tallene.
 * MineTall: Lite klientprogram.
 */

import static javax.swing.JOptionPane.*;
class Tallrekke {
  private int[] tabell = new int[2];  // liten for testformål
  private int antallTall = 0;

  public void leggTilTall(int nyttTall) {
    if (antallTall == tabell.length) {
      utvidTabell();
    }
    tabell[antallTall] = nyttTall;
    antallTall++;
  }

  public String toString() {
    String res = "";
    for (int i = 0; i < antallTall; i++) {
      res += tabell[i] + " ";
    }
    return res;
  }

  /* Hjelpemetode for å "utvide" tabellen */
  private void utvidTabell() {
    int[] nyTab = new int[tabell.length + 2];  // ny og større tabell
    for (int i = 0; i < tabell.length; i++) {  // kopierer data over til ny tabell
      nyTab[i] = tabell[i];
    }
    tabell = nyTab;  // setter objektvariabelen lik den nye tabellen
  }
}

/**
 * Leser inn tall fra brukeren og lagrer i et Tallrekke-objekt.
 * Brukeren skriver inn et og et tall og avslutter med Esc.
 * Til slutt skrives alle tallene ut i en meldingsboks.
 */
class MineTall {
  public static void main(String[] args) {
    Tallrekke enRekke = new Tallrekke();
    String tallLest = showInputDialog("Skriv tall, avslutt med Esc.");
    while (tallLest != null) {
      int tall = Integer.parseInt(tallLest);
      enRekke.leggTilTall(tall);
      tallLest = showInputDialog("Skriv tall, avslutt med Esc.");
    }
  showMessageDialog(null, "Tallrekken: " + enRekke.toString());
  }
}