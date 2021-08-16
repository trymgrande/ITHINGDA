/**
 * Oppussingsprosjekt.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen vedlikeholder et register over flater og et register over materialtyper.
 * En videreutvikling av klassen med samme navn fra kapittel 13.
 */

import java.util.ArrayList;
class Oppussingsprosjekt {
  private final String navn;
  private ArrayList <Flate>flatene = new ArrayList<Flate>();
  private ArrayList <Materiale>materialene = new ArrayList<Materiale>();

  public Oppussingsprosjekt(String navn) {
    this.navn = navn;
  }

  public String getNavn() {
    return navn;
  }

  /**
   * Metoden registrerer en ny materialtype. Dersom materialtype med dette
   * navnet er registrert fra før, returneres denne materialtypen,
   * ellers returneres den nye materialtype.
   */
  public Materiale registrerNyttMateriale(Materiale nyMaterialtype) {
    int indeks = materialene.indexOf(nyMaterialtype);
    if (indeks < 0) {
      materialene.add(nyMaterialtype);
      return nyMaterialtype;
    } else {
      return materialene.get(indeks);
    }
  }

  /**
   * Metoden søker etter en materialtype med et gitt navn.
   * Dersom det ikke finnes, returneres null.
   */
  public Materiale finnMateriale(String materialnavn) {
    for (Materiale dette : materialene) {
      String detteNavn = dette.getNavn();
      if (detteNavn.equals(materialnavn)) {
        return dette;
      }
    }
    return null;
  }

  /**
   * Metoden registrerer en ny flate. Dersom en flate med dette
   * navnet er registrert fra før, returneres denne flaten,
   * ellers returneres den nye flaten.
   */
  public Flate registrerNyFlate(Flate nyFlate) {
    int indeks = flatene.indexOf(nyFlate);
    if (indeks < 0) {
      flatene.add(nyFlate);
      /*
       * Materialtype kan være knyttet til denne flaten.
       * Hvis denne materialtypen ikke er registrert fra før, registrerer vi den nå.
       */
      Materiale materiale = nyFlate.getMaterialtype();
      if (materiale != null) {
        registrerNyttMateriale(materiale);
      }
      return nyFlate;
    } else {
      return flatene.get(indeks);  // flate med dette navnet er registrert fra før
    }
  }

  /**
   * Metoden søker etter en flate med et gitt navn.
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
      Materiale materialtype = flaten.getMaterialtype();
      if (materialtype != null) {
        totalpris += materialtype.beregnTotalpris(flaten);
      }
    }
    return totalpris;
  }

  /**
   * Metoder som kan brukes ved sekvensiell søk gjennom
   *  alle malinger eller alle flater
   */

  public int finnAntMaterialer() {
    return materialene.size();
  }

  public Materiale finnMateriale(int indeks){
    return (indeks >= 0 && indeks < materialene.size()) ? materialene.get(indeks) : null;
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
      Materiale materiale = flaten.getMaterialtype();
      if (materiale != null) {
        double behov = materiale.beregnMaterialbehov(flaten);
        double pris = materiale.beregnTotalpris(flaten);
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
    resultat += "\nAlle materialtyper:\n";
    for (Materiale etMateriale : materialene) {
      resultat += etMateriale + "\n";
    }
    return resultat;
  }
}
