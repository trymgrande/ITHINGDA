/**
 * FigurKlient.java   - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Lager en tabell av figurer, som viser at polymorfien virker
 */

class FigurKlient {
  public static void main(String[] args) {
    Sirkel enSirkel = new Sirkel(10);
    Trekant enTrekant = new Trekant(5, 6);
    Kvadrat etKvadrat = new Kvadrat(4);

    /* Arealer hver for seg */
    System.out.println("Arealet til sirkelen: " + enSirkel.beregnAreal());
    System.out.println("Arealet til trekanten: " + enTrekant.beregnAreal());
    System.out.println("Arealet til kvadratet: " + etKvadrat.beregnAreal());

    /* Behandler så alle figurene under ett */
    Figur[] figurtabell = new Figur[3];
    figurtabell[0] = enSirkel;
    figurtabell[1] = enTrekant;
    figurtabell[2] = etKvadrat;

    for (int i = 0; i < figurtabell.length; i++) {
      System.out.println("Arealet er " + figurtabell[i].beregnAreal()
            + " for " + figurtabell[i].getClass().getName()); // henter klassenavnet
    }
  }
}

/* Utskrift:
Arealet til sirkelen: 314.1592653589793
Arealet til trekanten: 15.0
Arealet til kvadratet: 16.0
Arealet er 314.1592653589793 for Sirkel
Arealet er 15.0 for Trekant
Arealet er 16.0 for Kvadrat
*/