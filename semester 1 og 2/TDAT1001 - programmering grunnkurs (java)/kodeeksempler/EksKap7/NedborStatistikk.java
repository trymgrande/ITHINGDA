/**
 * NedborStatistikk.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder to klasser:
 * Maned: Immutabel klasse som lagrer nedb�rdata for en m�ned.
 * Tilbyr operasjoner for � analysere disse: finn gjennomsnitt,
 *           finn total, finn t�rre dager, osv.
 * NedborStatistikk: Enkel klient for enkel utpr�ving av metodene
 */

class Maned {
  private final String mndNavn;
  private final int[] nedb�r;

  /**
   * Konstrukt�ren lager en kopi av argumentet med nedb�rdata.
   */
public Maned(String mndNavn, int[] nedb�r) {
  this.mndNavn = mndNavn;
  int antDager = nedb�r.length;
  this.nedb�r = new int[antDager];
  for (int i = 0; i < antDager; i++) {
    this.nedb�r[i] = nedb�r[i];
  }
}

  public String getMndNavn() {
    return mndNavn;
  }

  /**
   * Metoden finner nedb�ren p� en gitt dag. Returnerer -1 dersom ugyldig indeks.
   */
  public int finnNedb�r(int indeks) {
    return (indeks >= 0 && indeks < nedb�r.length) ? nedb�r[indeks] : -1;
  }

  /**
   * Metoden finner antall dager i m�neden.
   */
  public int finnAntDager() {
    return nedb�r.length;
  }

  /**
   * Metoden finner maksimalnedb�ren i m�neden.
   */
  public int finnMaksimum() {
    int maks = 0;
    if (nedb�r.length > 0) {
      maks = nedb�r[0];
      for (int i = 1; i < nedb�r.length; i++) {
        if (nedb�r[i] > maks) {
          maks = nedb�r[i];
        }
      }
    }
    return maks;
  }

  /**
   * Metoden finner gjennomsnittlig nedb�r i m�neden rundet av til n�rmeste heltall.
   * Returnerer -1 hvis antall dager er 0.
   */
  public int finnGjSnitt() {
    int sum = 0;
    for (int i = 0; i < nedb�r.length; i++) {
      sum += nedb�r[i];
    }
    if (nedb�r.length > 0) {
      double snitt = (double) sum / (double) nedb�r.length;
      return (int) (snitt + 0.5);
    } else {
      return -1;
    }
  }

  /**
   * Metoden finner antall dager uten nedb�r.
   */
  public int finnAntT�rreDager() {
    int antall = 0;
    for (int i = 0; i < nedb�r.length; i++) {
      if (nedb�r[i] == 0) {
        antall++;
      }
    }
    return antall;
  }

  /**
   * Metoden finner hvilke dager som har nedb�r lik maksimalnedb�ren for m�neden.
   * Returnerer en tabell med indekser til disse dagene.
   */
  public int[] finnDgMaks() {
    int maks = finnMaksimum();

    /* Lager en tabell med plass til alle dagene, det verst tenkelige ...*/
    int [] tabell = new int[nedb�r.length];
    int antMaks = 0;
    for (int i = 0; i < nedb�r.length; i++) {
      if (nedb�r[i] == maks) {
        tabell[antMaks] = i;
        antMaks++;
      }
    }

    /* Lager n� en tabell med eksakt riktig st�rrelse og kopierer over. */
    int[] maksDager = new int[antMaks];
    for (int i = 0; i < antMaks; i++) {
      maksDager[i] = tabell[i];
    }
    return maksDager;
  }

  public String toString() {
    String res = "";
    for (int i = 0; i < nedb�r.length; i++) {
      res += nedb�r[i] + " ";
    }
    return res;
  }
}

/**
 * Enkel utpr�ving av metodene.
 */
class NedborStatistikk {
  public static void main(String[] args) {
    int[] nedb�r = {1, 4, 0, 4, 3};  // liten m�ned for testform�l
    /*int[] nedb�r = {};  // har ogs� pr�vd med tabell med lengde 0
     *int[] nedb�r = {1};  // og lengde 1 */
    Maned januar = new Maned("Januar", nedb�r);
    System.out.println("Statistikk for " + januar.getMndNavn());
    System.out.println("Maksimum: " + januar.finnMaksimum());
    System.out.println("Gjennomsnitt: " + januar.finnGjSnitt());
    System.out.println("Antall t�rre dager: " + januar.finnAntT�rreDager());

    for (int i = 0; i < januar.finnAntDager(); i++) {
      System.out.println("Nedb�r dag nr " + (i + 1) + " er "
        + januar.finnNedb�r(i));
    }

    int[] maksDg = januar.finnDgMaks();
    for (int i = 0; i < maksDg.length; i++) {
      System.out.println("Maks. nedb�r dag nr: " + (maksDg[i] + 1));
    }
  }
}

/* Kj�ring av programmet:
Statistikk for Januar
Maksimum: 4
Gjennomsnitt: 2
Antall t�rre dager: 1
Nedb�r dag nr 1 er 1
Nedb�r dag nr 2 er 4
Nedb�r dag nr 3 er 0
Nedb�r dag nr 4 er 4
Nedb�r dag nr 5 er 3
Maks. nedb�r dag nr: 2
Maks. nedb�r dag nr: 4
*/