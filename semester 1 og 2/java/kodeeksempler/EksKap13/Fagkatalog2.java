/**
 * Fagkatalog2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klasse for å vedlikeholde en fagkatalog.
 * Samme funksjonalitet som Fagkatalog, men her brukes
 * ArrayList i stedet for tabell.
 */
import java.util.ArrayList;
class Fagkatalog2 {
  private ArrayList<Fag> fagene = new ArrayList<Fag>();

  /**
   * Registrerer nytt fag.
   * Returnerer false hvis fag med denne koden eksisterer fra før.
   */
  public boolean registrerNyttFag(String fagkode, String fagnavn) {
    if (finnFagGittKode(fagkode) != null) {
      return false;   // RETUR. Fag er allerede registrert.
    }
    fagene.add(new Fag(fagkode, fagnavn));
    return true;
  }

  /**
   * Returnerer antall fag registrert.
   */
  public int finnAntFag() {
    return fagene.size();
  }

  /**
   * Returnerer fag med bestemt indeks.
   * Returnerer null hvis ugyldig indeks.
   */
  public Fag finnFagGittIndeks(int indeks) {
    return (indeks >= 0 && indeks < fagene.size()) ? fagene.get(indeks) : null;
  }

  /**
   * Returnerer fag med gitt fagkode.
   * Returnerer null hvis fag ikke finnes.
   */
  public Fag finnFagGittKode(String fagkode) {
    if (fagkode != null) {
      for (Fag etFag : fagene) {
        if (etFag.getFagkode().equals(fagkode.trim())) {
          return etFag;
        }
      }
    }
    return null;
  }

  /**
   * Oppdaterer antall studenter på bestemt fag.
   * Returnerer false hvis fag ikke finnes.
   */
  public boolean oppdaterAntStud(String fagkode, int antStud) {
    Fag f = finnFagGittKode(fagkode);
    if (f != null) {
      f.setAntStud(antStud);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sletter et fag fra katalogen.
   * Returnerer false hvis fag ikke finnes.
   */
  public boolean slettFag(String fagkode) {
    if (fagkode != null) {
      for (int i = 0; i < fagene.size(); i++) {
        if (fagene.get(i).getFagkode().equals(fagkode.trim())) { // faget er funnet
          fagene.remove(i);
          return true;
        }
      }
    }
    return false; // fag ikke funnet, sletter ingenting
  }

  /**
   * toString()
   * Bygger opp en resultatstreng ved å sende toString()-meldingen til hvert
   * enkelt fagobjekt. Legger inn linjeskift mellom hvert fag.
   */
  public String toString() {
    String resultat = "";
    for (Fag etFag : fagene) {
      resultat += etFag.toString() + "\n";
    }
    return resultat;
  }

  /**
   *
   * Testprogram for klassen. Ett testsett pr metode.
   * Tester med tom katalog og katalog med fire fag, slik at utvidTabell() blir prøvd
   */
   public static void main(String[] args) {
     System.out.println("Totalt antall tester: 6");
     Fagkatalog2 kat0 = new Fagkatalog2(); // tom katalog
     Fagkatalog2 kat = new Fagkatalog2(); // blir fylt med fire fag
     boolean ok1 = kat.registrerNyttFag("LC191D", "Videregående programmering");
     boolean ok2 = kat.registrerNyttFag("LV172D", "Programmering i Java");
     boolean ok3 = kat.registrerNyttFag("LO347D", "Web-applikasjoner");
     boolean ok4 = kat.registrerNyttFag("LV172D", "Programmering i Java");
     boolean ok5 = kat.registrerNyttFag("LC331D", "IT, miljø og samfunn");
     if (ok1 && ok2 && ok3 && !ok4 && ok5) {
       System.out.println("Fagkatalog2: Test 1 vellykket");
     }

     if (kat.finnAntFag() == 4 && kat0.finnAntFag() == 0) {
       System.out.println("Fagkatalog2: Test 2 vellykket");
     }

     Fag f1 = kat.finnFagGittIndeks(2);
     Fag f2 = kat.finnFagGittIndeks(4);
     Fag ff1 =  kat0.finnFagGittIndeks(0);
     if (f1.getFagkode().equals("LO347D") && f2 == null && ff1 == null) {
       System.out.println("Fagkatalog2: Test 3 vellykket");
     }

     Fag f3 = kat.finnFagGittKode("LC191D");
     Fag f4 = kat.finnFagGittKode("LC331D");
     Fag f5 = kat.finnFagGittKode("LO331D");
     Fag ff2 = kat0.finnFagGittKode("LC191D");
     if (f3.getFagnavn().equals("Videregående programmering")
        && f4.getFagnavn().equals("IT, miljø og samfunn") && f5 == null && ff2 == null) {
       System.out.println("Fagkatalog2: Test 4 vellykket");
     }

    boolean ok6 = kat.oppdaterAntStud("LC191D", 30);
    boolean ok7 = kat.oppdaterAntStud("LV191D", 30);
    boolean okk1 = kat0.oppdaterAntStud("LV191D", 30);
    Fag f6 = kat.finnFagGittKode("LC191D");
    if (ok6 && !ok7 &&  f6.getAntStud() == 30 && !okk1) {
      System.out.println("Fagkatalog2: Test 5 vellykket");
    }

    boolean ok8 = kat.slettFag("LC331D");  // nå er det tre fag igjen
    boolean okk2 = kat0.slettFag("LC191D");
    kat0.registrerNyttFag("LC191D", "Videregående programmering");
    boolean okk3 = kat0.slettFag("LC191D");  // sletter ett fag
    if (ok8 && kat.finnAntFag() == 3 && !okk2 && okk3 && kat0.finnAntFag() == 0) {
      System.out.println("Fagkatalog2: Test 6 vellykket");
    }
  }
}