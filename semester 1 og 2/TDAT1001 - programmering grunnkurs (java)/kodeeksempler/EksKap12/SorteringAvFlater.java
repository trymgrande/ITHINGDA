/**
 * SorteringAvFlater.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Prøver sorteringsmetodene på en tabell av Flate-objekter
 */
class SorteringAvFlater {
  public static void main(String[] args) {
    Flate[] flater = {new Flate("E", 10, 5), new Flate("B", 1, 5),
                             new Flate("F", 5, 5), new Flate("A", 5, 5)};

    System.out.println("Standardsortering (iht. navn)");
    Sortering.sorterObjekter(flater);
    for (int i = 0; i < flater.length; i++) {
      System.out.println(flater[i] + " Areal = " + flater[i].beregnAreal() + ".");
    }

    System.out.println("\nSortering iht. areal (med komparator)");
    Sortering.sorterObjekter(flater, new FlateKompAreal());
    for (int i = 0; i < flater.length; i++) {
      System.out.println(flater[i] + " Areal = " + flater[i].beregnAreal() + ".");
    }
  }
}
/* Utskrift:
Standardsortering (iht. navn)
Flate: A, bredde: 5,00 m, lengde: 5,00 m. Areal = 25.0.
Flate: B, bredde: 5,00 m, lengde: 1,00 m. Areal = 5.0.
Flate: E, bredde: 5,00 m, lengde: 10,00 m. Areal = 50.0.
Flate: F, bredde: 5,00 m, lengde: 5,00 m. Areal = 25.0.

Sortering iht. areal (med komparator)
Flate: B, bredde: 5,00 m, lengde: 1,00 m. Areal = 5.0.
Flate: A, bredde: 5,00 m, lengde: 5,00 m. Areal = 25.0.
Flate: F, bredde: 5,00 m, lengde: 5,00 m. Areal = 25.0.
Flate: E, bredde: 5,00 m, lengde: 10,00 m. Areal = 50.0.
*/