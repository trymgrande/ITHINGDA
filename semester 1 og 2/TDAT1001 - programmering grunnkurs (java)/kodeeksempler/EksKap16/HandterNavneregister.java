/**
 * HandterNavneregister.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet vedlikeholder en tekstfil med navn.
 */

import java.io.*;
import static javax.swing.JOptionPane.*;
class HandterNavneregister {
  public static void main(String[] args) throws IOException {
    String filnavn = "navnefil.txt";
    /*
     * Leser inn alle navnene og skriver dem ut på skjermen
     */
    FileReader leseforbTilFil = new FileReader(filnavn);
    BufferedReader leser = new BufferedReader(leseforbTilFil);

    String etNavn = leser.readLine();
    String innlesteNavn = "Følgende navn er registrert:";
    while(etNavn != null) {   // null betyr filslutt
      innlesteNavn += ("\n" + etNavn);
      etNavn = leser.readLine();
    }
    leser.close();
    showMessageDialog(null, innlesteNavn);
    /*
     * Skal nye navn registreres?
     */
    int svar = showConfirmDialog(null, "Skal flere navn registreres?",
                                       "Navneregister", YES_NO_OPTION);
    if (svar == YES_OPTION) {
      FileWriter skriveforbTilFil = new FileWriter(filnavn, true);
      PrintWriter skriver = new PrintWriter(new BufferedWriter(skriveforbTilFil));
      while (svar == YES_OPTION) {
        String nyttNavn = showInputDialog("Oppgi et navn: ");
        skriver.println(nyttNavn);
        svar = showConfirmDialog(null, "Skal flere navn registreres?",
                                       "Navneregister", YES_NO_OPTION);
      }
      skriver.close();
    }
  }
}

/*
=> Datafilen før programmet kjøres:
Anne Eriksen
Tove Ås
Berit Jensen

=> Utskriftvinduet ved kjøring:
Anne Eriksen
Tove Ås
Berit Jensen
***Inndata: Spørsmål: Skal flere navn registreres? Svar: Ja.
***Inndata: Oppgi et navn:  Toril Sollien
***Inndata: Spørsmål: Skal flere navn registreres? Svar: Ja.
***Inndata: Oppgi et navn:  Arne Håkonsen
***Inndata: Spørsmål: Skal flere navn registreres? Svar: Nei.

=> Datafilen etter at programmet er kjørt:
Anne Eriksen
Tove Ås
Berit Jensen
Toril Sollien
Arne Håkonsen
*/