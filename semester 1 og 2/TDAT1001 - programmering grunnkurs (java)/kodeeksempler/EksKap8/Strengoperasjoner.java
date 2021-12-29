/**
 * Strengoperasjoner.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn en tekst og gjør enkle strengoperasjoner på den.
 * Resultatene samles i en streng før den skrives ut til slutt.
 */

import static javax.swing.JOptionPane.*;
class Strengoperasjoner {
  public static void main(String[] args) {
    String tekst = showInputDialog("Skriv en tekst: ");
    String utenBlankeForanOgbak = tekst.trim();
    String resultat = "Blanke fjernet: " + utenBlankeForanOgbak;
    String store = tekst.toUpperCase();
    resultat = resultat + "\nBare store bokstaver: " + store;
    String små = tekst.toLowerCase();
    resultat = resultat + "\nBare små bokstaver: " + små;
    char førsteBokstav = tekst.charAt(0);
    resultat = resultat +  "\nFørste bokstav: " + førsteBokstav;
    char andreBokstav = tekst.charAt(1);
    resultat = resultat +  "\nAndre bokstav: " + andreBokstav;
    int antTegn = tekst.length();
    resultat = resultat +  "\nLengden til teksten: " + antTegn;
    showMessageDialog(null, resultat);
  }
}
/* Utskriftvindu:
Blanke fjernet: Anne Katrine Ås
Bare store bokstaver: ANNE KATRINE ÅS
Bare små bokstaver: anne katrine ås
Første bokstav: A
Andre bokstav: n
Lengden til teksten: 15
*/