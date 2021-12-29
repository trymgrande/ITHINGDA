/*
 * SorterOgSoekArrayListJavaAPI.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Program som viser hvordan vi bruker Collections-klassen til å
 * sortere og søke i ArrayList-objekter. Tar også med eksempel
 * på bruk av indexof() og lastIndexOf() i klassen ArrayList.
 */

import java.util.*;

class SorterOgSoekArrayListJavaAPI {
  public static void main(String[] args) {

    /* Legger seks flater i en ArrayList. */
    ArrayList<Flate> flater = new ArrayList<Flate>();
    flater.add(new Flate("B", 3, 4));
    flater.add(new Flate("E", 2, 3.5));
    flater.add(new Flate("A", 5, 2));
    flater.add(new Flate("C", 2, 4));
    flater.add(new Flate("F", 4, 3));
    flater.add(new Flate("D", 4, 2.4));

    /* Skriver ut arealene i den opprinnelige rekkefølgen. */
    System.out.println("Opprinnelig rekkefølge:");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }

    /*
     * Søker. Som argument må vi sende inn en referanse til et objekt.
     * For at testen på likhet skal slå ut, må dette objektet ha samme navn som
     * det vi søker etter.
     */
    Flate søkeFlate1 = new Flate("B", 2, 6); // flate med navn B (finnes)
    Flate søkeFlate2 = new Flate("H", 2, 6.5); // flate med navn H (finnes ikke)

    /* Søk i usortert liste ved hjelp av metoder i klassen ArrayList. */
    /* Disse metodene bruker equals() for å sammenligne objekter. */
    System.out.println("Resultater søk med indexOf() og lastIndexof(): ");
    int res1 = flater.indexOf(søkeFlate1);
    System.out.println("Flate med navn B er funnet forfra på indeks " + res1);
    res1 = flater.lastIndexOf(søkeFlate1);
    System.out.println("Flate med navn B er funnet bakfra på indeks " + res1);
    int res2 = flater.indexOf(søkeFlate2);
    System.out.println("Flate med navn H finnes ikke, resultat fra søk: " + res2);

    /* Sorterer og skriver deretter ut i sortert rekkefølge. */
    Collections.sort(flater);
    System.out.println("Sortert rekkefølge (navn):");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }

    /* Binærsøk, listen må være sortert iht. compareTo(). */
    System.out.println("Resultater binærsøk: ");
    res1 = Collections.binarySearch(flater, søkeFlate1);
    System.out.println("Flate med navn B er funnet på indeks " + res1);
    res2 = Collections.binarySearch(flater, søkeFlate2); // finnes ikke
    System.out.println("Flate med navn H er ikke funnet." +
                       " Kan eventuelt legges inn på indeks " + (-res2 - 1));

    /* Sorterer i henhold til areal, det vil si at vi må bruke et FlateKompAreal-objekt. */
    Collections.sort(flater, new FlateKompAreal());
    System.out.println("Sortert rekkefølge (areal):");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }
  }
}

/* Utskrift:
Opprinnelig rekkefølge:
B har areal 12.0
E har areal 7.0
A har areal 10.0
C har areal 8.0
F har areal 12.0
D har areal 9.6
Resultater søk med indexOf() og lastIndexof():
Flate med navn B er funnet forfra på indeks 0
Flate med navn B er funnet bakfra på indeks 0
Flate med navn H finnes ikke, resultat fra søk: -1
Sortert rekkefølge (navn):
A har areal 10.0
B har areal 12.0
C har areal 8.0
D har areal 9.6
E har areal 7.0
F har areal 12.0
Resultater binærsøk:
Flate med navn B er funnet på indeks 1
Flate med navn H er ikke funnet. Kan eventuelt legges inn på indeks 6
Sortert rekkefølge (areal):
E har areal 7.0
C har areal 8.0
D har areal 9.6
A har areal 10.0
B har areal 12.0
F har areal 12.0
*/
