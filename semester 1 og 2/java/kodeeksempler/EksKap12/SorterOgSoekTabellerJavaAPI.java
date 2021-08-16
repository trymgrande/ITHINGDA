/**
 * SorterOgSoekTabellerJavaAPI.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Klassen viser hvordan metoder i klassen java.util.Arrays kan brukes til å sortere
 * tabeller av objekter. Eksempel på binærsøk er også tatt med.
 */

class SorterOgSoekTabellerJavaAPI {
  public static void main(String[] args) {

    /* Legger seks flater i en tabell */
    Flate[] flater = {new Flate("D", 3, 4), new Flate("B", 2, 3.5), new Flate("G", 5, 2),
                      new Flate("A", 2, 4), new Flate("C", 4, 3), new Flate("F", 4, 2.4)};

    /* Skriver ut arealene i den opprinnelige rekkefølgen */
    System.out.println("Opprinnelig rekkefølge:");
    for (int i = 0; i < flater.length; i++) {
      System.out.println(flater[i].getNavn() + " har areal " + flater[i].beregnAreal());
    }

    /* Sorterer i hht standard sortering (Comparable) */
    java.util.Arrays.sort(flater);
    System.out.println("Sortert rekkefølge (navn):");
    for (int i = 0; i < flater.length; i++) {
      System.out.println(flater[i].getNavn() + " har areal " + flater[i].beregnAreal());
    }

    /* I denne sorterte tabellen kan vi søke etter en flate med et bestemt navn */
    Flate søkeFlate1 = new Flate("C", 2, 3);
    int res1 = java.util.Arrays.binarySearch(flater, søkeFlate1); // finnes
    System.out.println("Flate med navn C er funnet på indeks " + res1);

    Flate søkeFlate2 = new Flate("EE", 2, 3);
    int res2 = java.util.Arrays.binarySearch(flater, søkeFlate2); // finnes ikke
    System.out.println("Flate med navn EE er ikke funnet."
                     + " Kan eventuelt legges inn på indeks " + (-res2 - 1));

    /* Sorterer i henhold til areal, det vil si at vi må bruke et FlateKompAreal-objekt */
    java.util.Arrays.sort(flater, new FlateKompAreal());
    System.out.println("Sortert rekkefølge (areal):");
    for (int i = 0; i < flater.length; i++) {
      System.out.println(flater[i].getNavn() + " har areal " + flater[i].beregnAreal());
    }
  }
}

/* Kjøring av programmet:
Opprinnelig rekkefølge:
D har areal 12.0
B har areal 7.0
G har areal 10.0
A har areal 8.0
C har areal 12.0
F har areal 9.6
Sortert rekkefølge (navn):
A har areal 8.0
B har areal 7.0
C har areal 12.0
D har areal 12.0
F har areal 9.6
G har areal 10.0
Flate med navn C er funnet ps indeks 2
Flate med navn EE er ikke funnet. Kan eventuelt legges inn på indeks 4
Sortert rekkefølge (areal):
B har areal 7.0
A har areal 8.0
F har areal 9.6
G har areal 10.0
C har areal 12.0
D har areal 12.0
*/
