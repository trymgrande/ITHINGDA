/**
 * EnumEksempelMedToString.java  - "Programmering i Java", 4.utgave - 2009-07-01
 */

enum Ordrestatus {
  IKKE_BEHANDLET, UNDER_BEHANDLING,
  VENTER_PÅ_LEVERING, EKSPEDERT, BETALT;

 /**
 * toString()-metode som erstatter den som er arvet.
 * Alle bokstaver unntatt den første gjøres om til små.
 * Understrekingstegn erstattes med blank.
 */
  public String toString() {
    String tekst = super.toString(); // objektnavnet som tekst
    tekst = tekst.replace('_', ' ');
    char bokstav1 = tekst.charAt(0);  // første bokstav skal være stor
    String tekst2 = tekst.substring(1, tekst.length());  // resten av teksten
    tekst2 = tekst2.toLowerCase();
    return bokstav1 + tekst2;
  }
}

/**
 * Klient som løper gjennom alle objektene i klassen Ordrestatus
 * og sender meldiingen toString() til dem.
 */
class EnumEksempelMedToString {
  public static void main(String[] args) {
    for (Ordrestatus status : Ordrestatus.values()) {
      System.out.println(status);  // toString() underforstått
    }
  }
}

/* Eksempeldata:
Ikke behandlet
Under behandling
Venter på levering
Ekspedert
Betalt
*/
