/**
 * LesKarakter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet viser hvordan vi går i løkke inntil brukeren har skrevet
 * inn en gyldig bokstavkarakter.
 */
import static javax.swing.JOptionPane.*;
class LesKarakter {
  public static void main(String[] args) {

    /* Teknikk 1: Brukeren får ikke spesiallaget feilmelding. */
    String karakterS;
    char karakter;
    do {
      karakterS = showInputDialog("Skriv bokstavarakter (A-F): ");
      karakter = karakterS.charAt(0);   // henter første tegn
    } while (karakter < 'A' || karakter >  'F');
    showMessageDialog(null, "Gratulerer! Du klarte det, du skrev " + karakter + "!");

    /* Teknikk 2: Brukeren får spesiallaget feilmelding. */
    karakterS = showInputDialog("Skriv bokstavarakter (A-F): ");
    karakter = karakterS.charAt(0);
    while (karakter < 'A' || karakter >  'F') {
      karakterS = showInputDialog(
                                  "Ugyldig karakter, du må skrive en karakter i intervallet A-F. Prøv igjen: ");
      karakter = karakterS.charAt(0);
    }
    showMessageDialog(null,
                      "Gratulerer! Du klarte det enda en gang, du skrev " + karakter + "!");
  }
}

/* Eksempel på kjøring:
Skriv bokstavkarakter (A-F): b
Skriv bokstavkarakter (A-F): G
Skriv bokstavkarakter (A-F): E
Gratulerer! Du klarte det enda en gang, du skrev E!
Skriv bokstavkarakter (A-F): u
Ugyldig karakter, du må skrive en karakter i intervallet A-F. Prøv igjen: C
Du skrev C
*/
