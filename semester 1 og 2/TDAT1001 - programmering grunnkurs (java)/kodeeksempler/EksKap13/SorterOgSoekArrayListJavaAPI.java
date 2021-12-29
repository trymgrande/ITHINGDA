/*
 * SorterOgSoekArrayListJavaAPI.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Program som viser hvordan vi bruker Collections-klassen til �
 * sortere og s�ke i ArrayList-objekter. Tar ogs� med eksempel
 * p� bruk av indexof() og lastIndexOf() i klassen ArrayList.
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

    /* Skriver ut arealene i den opprinnelige rekkef�lgen. */
    System.out.println("Opprinnelig rekkef�lge:");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }

    /*
     * S�ker. Som argument m� vi sende inn en referanse til et objekt.
     * For at testen p� likhet skal sl� ut, m� dette objektet ha samme navn som
     * det vi s�ker etter.
     */
    Flate s�keFlate1 = new Flate("B", 2, 6); // flate med navn B (finnes)
    Flate s�keFlate2 = new Flate("H", 2, 6.5); // flate med navn H (finnes ikke)

    /* S�k i usortert liste ved hjelp av metoder i klassen ArrayList. */
    /* Disse metodene bruker equals() for � sammenligne objekter. */
    System.out.println("Resultater s�k med indexOf() og lastIndexof(): ");
    int res1 = flater.indexOf(s�keFlate1);
    System.out.println("Flate med navn B er funnet forfra p� indeks " + res1);
    res1 = flater.lastIndexOf(s�keFlate1);
    System.out.println("Flate med navn B er funnet bakfra p� indeks " + res1);
    int res2 = flater.indexOf(s�keFlate2);
    System.out.println("Flate med navn H finnes ikke, resultat fra s�k: " + res2);

    /* Sorterer og skriver deretter ut i sortert rekkef�lge. */
    Collections.sort(flater);
    System.out.println("Sortert rekkef�lge (navn):");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }

    /* Bin�rs�k, listen m� v�re sortert iht. compareTo(). */
    System.out.println("Resultater bin�rs�k: ");
    res1 = Collections.binarySearch(flater, s�keFlate1);
    System.out.println("Flate med navn B er funnet p� indeks " + res1);
    res2 = Collections.binarySearch(flater, s�keFlate2); // finnes ikke
    System.out.println("Flate med navn H er ikke funnet." +
                       " Kan eventuelt legges inn p� indeks " + (-res2 - 1));

    /* Sorterer i henhold til areal, det vil si at vi m� bruke et FlateKompAreal-objekt. */
    Collections.sort(flater, new FlateKompAreal());
    System.out.println("Sortert rekkef�lge (areal):");
    for (Flate enFlate : flater) {
      System.out.println(enFlate.getNavn() + " har areal " + enFlate.beregnAreal());
    }
  }
}

/* Utskrift:
Opprinnelig rekkef�lge:
B har areal 12.0
E har areal 7.0
A har areal 10.0
C har areal 8.0
F har areal 12.0
D har areal 9.6
Resultater s�k med indexOf() og lastIndexof():
Flate med navn B er funnet forfra p� indeks 0
Flate med navn B er funnet bakfra p� indeks 0
Flate med navn H finnes ikke, resultat fra s�k: -1
Sortert rekkef�lge (navn):
A har areal 10.0
B har areal 12.0
C har areal 8.0
D har areal 9.6
E har areal 7.0
F har areal 12.0
Resultater bin�rs�k:
Flate med navn B er funnet p� indeks 1
Flate med navn H er ikke funnet. Kan eventuelt legges inn p� indeks 6
Sortert rekkef�lge (areal):
E har areal 7.0
C har areal 8.0
D har areal 9.6
A har areal 10.0
B har areal 12.0
F har areal 12.0
*/
