/**
 * NedborStatistikk.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder to klasser:
 * Maned: Immutabel klasse som lagrer nedbørdata for en måned.
 * Tilbyr operasjoner for å analysere disse: finn gjennomsnitt,
 *           finn total, finn tørre dager, osv.
 * NedborStatistikk: Enkel klient for enkel utprøving av metodene
 */

class Maned {
  private final String mndNavn;
  private final int[] nedbør;

  /**
   * Konstruktøren lager en kopi av argumentet med nedbørdata.
   */
public Maned(String mndNavn, int[] nedbør) {
  this.mndNavn = mndNavn;
  int antDager = nedbør.length;
  this.nedbør = new int[antDager];
  for (int i = 0; i < antDager; i++) {
    this.nedbør[i] = nedbør[i];
  }
}

  public String getMndNavn() {
    return mndNavn;
  }

  /**
   * Metoden finner nedbøren på en gitt dag. Returnerer -1 dersom ugyldig indeks.
   */
  public int finnNedbør(int indeks) {
    return (indeks >= 0 && indeks < nedbør.length) ? nedbør[indeks] : -1;
  }

  /**
   * Metoden finner antall dager i måneden.
   */
  public int finnAntDager() {
    return nedbør.length;
  }

  /**
   * Metoden finner maksimalnedbøren i måneden.
   */
  public int finnMaksimum() {
    int maks = 0;
    if (nedbør.length > 0) {
      maks = nedbør[0];
      for (int i = 1; i < nedbør.length; i++) {
        if (nedbør[i] > maks) {
          maks = nedbør[i];
        }
      }
    }
    return maks;
  }

  /**
   * Metoden finner gjennomsnittlig nedbør i måneden rundet av til nærmeste heltall.
   * Returnerer -1 hvis antall dager er 0.
   */
  public int finnGjSnitt() {
    int sum = 0;
    for (int i = 0; i < nedbør.length; i++) {
      sum += nedbør[i];
    }
    if (nedbør.length > 0) {
      double snitt = (double) sum / (double) nedbør.length;
      return (int) (snitt + 0.5);
    } else {
      return -1;
    }
  }

  /**
   * Metoden finner antall dager uten nedbør.
   */
  public int finnAntTørreDager() {
    int antall = 0;
    for (int i = 0; i < nedbør.length; i++) {
      if (nedbør[i] == 0) {
        antall++;
      }
    }
    return antall;
  }

  /**
   * Metoden finner hvilke dager som har nedbør lik maksimalnedbøren for måneden.
   * Returnerer en tabell med indekser til disse dagene.
   */
  public int[] finnDgMaks() {
    int maks = finnMaksimum();

    /* Lager en tabell med plass til alle dagene, det verst tenkelige ...*/
    int [] tabell = new int[nedbør.length];
    int antMaks = 0;
    for (int i = 0; i < nedbør.length; i++) {
      if (nedbør[i] == maks) {
        tabell[antMaks] = i;
        antMaks++;
      }
    }

    /* Lager nå en tabell med eksakt riktig størrelse og kopierer over. */
    int[] maksDager = new int[antMaks];
    for (int i = 0; i < antMaks; i++) {
      maksDager[i] = tabell[i];
    }
    return maksDager;
  }

  public String toString() {
    String res = "";
    for (int i = 0; i < nedbør.length; i++) {
      res += nedbør[i] + " ";
    }
    return res;
  }
}

/**
 * Enkel utprøving av metodene.
 */
class NedborStatistikk {
  public static void main(String[] args) {
    int[] nedbør = {1, 4, 0, 4, 3};  // liten måned for testformål
    /*int[] nedbør = {};  // har også prøvd med tabell med lengde 0
     *int[] nedbør = {1};  // og lengde 1 */
    Maned januar = new Maned("Januar", nedbør);
    System.out.println("Statistikk for " + januar.getMndNavn());
    System.out.println("Maksimum: " + januar.finnMaksimum());
    System.out.println("Gjennomsnitt: " + januar.finnGjSnitt());
    System.out.println("Antall tørre dager: " + januar.finnAntTørreDager());

    for (int i = 0; i < januar.finnAntDager(); i++) {
      System.out.println("Nedbør dag nr " + (i + 1) + " er "
        + januar.finnNedbør(i));
    }

    int[] maksDg = januar.finnDgMaks();
    for (int i = 0; i < maksDg.length; i++) {
      System.out.println("Maks. nedbør dag nr: " + (maksDg[i] + 1));
    }
  }
}

/* Kjøring av programmet:
Statistikk for Januar
Maksimum: 4
Gjennomsnitt: 2
Antall tørre dager: 1
Nedbør dag nr 1 er 1
Nedbør dag nr 2 er 4
Nedbør dag nr 3 er 0
Nedbør dag nr 4 er 4
Nedbør dag nr 5 er 3
Maks. nedbør dag nr: 2
Maks. nedbør dag nr: 4
*/