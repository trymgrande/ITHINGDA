/**
 * Oppussingsprosjekt.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen vedlikeholder et register over flater og et register over malingstyper.
 * En klient kan legge inn referanser til objekter.
 * Klienten kan ogs� hente ut en referanse til et objekt, og via denne
 * gj�re endringer i datainnholdet i objektet, dersom det er mutabelt.
 * Her betyr det konkret at klienten kan hente ut et flate-
 * objekt og endre malingstypen for dette objektet.
 *
 * Klassene Maling og Flate er ikke vist i boka.
 * Se eksempelsamlingen p� bokas hjemmeside.
 */

import java.util.ArrayList;
class Oppussingsprosjekt implements java.io.Serializable {
  private final String navn;
  private ArrayList <Flate>flatene = new ArrayList<Flate>();
  private ArrayList <Maling>malingene = new ArrayList<Maling>();

  public Oppussingsprosjekt(String navn) {
    this.navn = navn;
  }

  public String getNavn() {
    return navn;
  }

  /**
   * Metoden registrerer en ny malingstype. Dersom en malingstype med dette
   * navnet er registrert fra f�r, returneres denne malingstypen,
   * ellers returneres den nye malingstypen.
   */
  public Maling registrerNyMaling(Maling nyMalingstype) {
    int indeks = malingene.indexOf(nyMalingstype);
    if (indeks < 0) {
      malingene.add(nyMalingstype);
      return nyMalingstype;
    } else {
      return malingene.get(indeks);
    }
  }

  /**
   * Metoden s�ker etter en malingstype med et gitt navn.
   * Dersom det ikke finnes, returneres null.
   */
  public Maling finnMaling(String malingnavn) {
    for (Maling denne : malingene) {
      String denneNavn = denne.getNavn();
      if (denneNavn.equals(malingnavn)) {
        return denne;
      }
    }
    return null;
  }

  /**
   * Metoden registrerer en ny flate. Dersom en flate med dette
   * navnet er registrert fra f�r, returneres denne flaten,
   * ellers returneres den nye flaten.
   */
  public Flate registrerNyFlate(Flate nyFlate) {
    int indeks = flatene.indexOf(nyFlate);
    if (indeks < 0) {
      flatene.add(nyFlate);
      /*
       * Malingstype kan v�re knyttet til denne flaten.
       * Hvis denne malingstypen ikke er registrert fra f�r, registrerer vi den n�.
       */
      Maling malingen = nyFlate.getMalingstype();
      if (malingen != null) {
        registrerNyMaling(malingen);
      }
      return nyFlate;
    } else {
      return flatene.get(indeks);  // flate med dette navnet er registrert fra f�r
    }
  }

  /**
   * Metoden s�ker etter en flate med et gitt navn.
   * Dersom det ikke finnes, returneres null.
   */
  public Flate finnFlate(String Flatenavn) {
    for (Flate denne : flatene) {
      String denneNavn = denne.getNavn();
      if (denneNavn.equals(Flatenavn)) {
        return denne;
      }
    }
    return null;
  }

  /**
   * Finner prosjektets totalpris
   */
  public double beregnTotalpris() {
    double totalpris = 0.0;
    for (Flate flaten : flatene) {
      Maling maling = flaten.getMalingstype();
      if (maling != null) {
        totalpris += maling.beregnTotalpris(flaten);
      }
    }
    return totalpris;
  }

  /**
   * Metoder som kan brukes ved sekvensiell s�k gjennom
   *  alle malinger eller alle flater
   */

  public int finnAntMalinger() {
    return malingene.size();
  }

  public Maling finnMaling(int indeks){
    return (indeks >= 0 && indeks < malingene.size()) ? malingene.get(indeks) : null;
  }

  public int finnAntFlater() {
    return flatene.size();
  }

  public Flate finnFlate(int indeks){
    return (indeks >= 0 && indeks < flatene.size()) ? flatene.get(indeks) : null;
  }

  /**
   * Metode som lager en tekststreng med detaljer om flater og valgte malingstyper.
   * Resultatet bygges opp i et Formatter-objekt.
   */
  public String lagPrisOgBehovDetaljer() {
    java.util.Formatter f = new java.util.Formatter();
    double totalpris = 0.0;
    for (Flate flaten : flatene) {
      f.format(flaten.toString());
      Maling maling = flaten.getMalingstype();
      if (maling != null) {
        double behov = maling.beregnAntLiter(flaten);
        double pris = maling.beregnTotalpris(flaten);
        f.format(", behov: %.2f liter, pris %.2f kr.\n", behov, pris);
        totalpris += pris;
      } else {
        f.format(" Malingstype ikke bestemt\n");
      }
    }
    f.format("Totalpris %.2f kr.", totalpris);
    return f.toString();
  }

  public String toString() {
    String resultat = "Alle flater:\n";
    for (Flate enFlate : flatene) {
      resultat += enFlate + "\n";
    }
    resultat += "\nAlle malingstyper:\n";
    for (Maling enMaling : malingene) {
      resultat += enMaling + "\n";
    }
    return resultat;
  }
}