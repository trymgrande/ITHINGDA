/**
 * Strengoperasjoner.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn en tekst og gj�r enkle strengoperasjoner p� den.
 * Resultatene samles i en streng f�r den skrives ut til slutt.
 */

import static javax.swing.JOptionPane.*;
class Strengoperasjoner {
  public static void main(String[] args) {
    String tekst = showInputDialog("Skriv en tekst: ");
    String utenBlankeForanOgbak = tekst.trim();
    String resultat = "Blanke fjernet: " + utenBlankeForanOgbak;
    String store = tekst.toUpperCase();
    resultat = resultat + "\nBare store bokstaver: " + store;
    String sm� = tekst.toLowerCase();
    resultat = resultat + "\nBare sm� bokstaver: " + sm�;
    char f�rsteBokstav = tekst.charAt(0);
    resultat = resultat +  "\nF�rste bokstav: " + f�rsteBokstav;
    char andreBokstav = tekst.charAt(1);
    resultat = resultat +  "\nAndre bokstav: " + andreBokstav;
    int antTegn = tekst.length();
    resultat = resultat +  "\nLengden til teksten: " + antTegn;
    showMessageDialog(null, resultat);
  }
}
/* Utskriftvindu:
Blanke fjernet: Anne Katrine �s
Bare store bokstaver: ANNE KATRINE �S
Bare sm� bokstaver: anne katrine �s
F�rste bokstav: A
Andre bokstav: n
Lengden til teksten: 15
*/