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
     * Leser inn alle navnene og skriver dem ut p� skjermen
     */
    FileReader leseforbTilFil = new FileReader(filnavn);
    BufferedReader leser = new BufferedReader(leseforbTilFil);

    String etNavn = leser.readLine();
    String innlesteNavn = "F�lgende navn er registrert:";
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
=> Datafilen f�r programmet kj�res:
Anne Eriksen
Tove �s
Berit Jensen

=> Utskriftvinduet ved kj�ring:
Anne Eriksen
Tove �s
Berit Jensen
***Inndata: Sp�rsm�l: Skal flere navn registreres? Svar: Ja.
***Inndata: Oppgi et navn:  Toril Sollien
***Inndata: Sp�rsm�l: Skal flere navn registreres? Svar: Ja.
***Inndata: Oppgi et navn:  Arne H�konsen
***Inndata: Sp�rsm�l: Skal flere navn registreres? Svar: Nei.

=> Datafilen etter at programmet er kj�rt:
Anne Eriksen
Tove �s
Berit Jensen
Toril Sollien
Arne H�konsen
*/