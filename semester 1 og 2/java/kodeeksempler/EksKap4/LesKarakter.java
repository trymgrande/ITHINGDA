/**
 * LesKarakter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet viser hvordan vi g�r i l�kke inntil brukeren har skrevet
 * inn en gyldig bokstavkarakter.
 */
import static javax.swing.JOptionPane.*;
class LesKarakter {
  public static void main(String[] args) {

    /* Teknikk 1: Brukeren f�r ikke spesiallaget feilmelding. */
    String karakterS;
    char karakter;
    do {
      karakterS = showInputDialog("Skriv bokstavarakter (A-F): ");
      karakter = karakterS.charAt(0);   // henter f�rste tegn
    } while (karakter < 'A' || karakter >  'F');
    showMessageDialog(null, "Gratulerer! Du klarte det, du skrev " + karakter + "!");

    /* Teknikk 2: Brukeren f�r spesiallaget feilmelding. */
    karakterS = showInputDialog("Skriv bokstavarakter (A-F): ");
    karakter = karakterS.charAt(0);
    while (karakter < 'A' || karakter >  'F') {
      karakterS = showInputDialog(
                                  "Ugyldig karakter, du m� skrive en karakter i intervallet A-F. Pr�v igjen: ");
      karakter = karakterS.charAt(0);
    }
    showMessageDialog(null,
                      "Gratulerer! Du klarte det enda en gang, du skrev " + karakter + "!");
  }
}

/* Eksempel p� kj�ring:
Skriv bokstavkarakter (A-F): b
Skriv bokstavkarakter (A-F): G
Skriv bokstavkarakter (A-F): E
Gratulerer! Du klarte det enda en gang, du skrev E!
Skriv bokstavkarakter (A-F): u
Ugyldig karakter, du m� skrive en karakter i intervallet A-F. Pr�v igjen: C
Du skrev C
*/
