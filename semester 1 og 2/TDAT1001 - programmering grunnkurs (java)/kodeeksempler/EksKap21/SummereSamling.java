/**
 * SummereSamling.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Illustrerer jokertegn i generiske typer.
 * Klassen Summerer kan summere elementene av en vilkårlig
 * ArrayList som er deklarert med en subklasse av Number som
 * type. Summereren returnerer en sum av datatypen double.
 *
 * Filen inneholder to klasser:
 * Summerer: Kan summere en samling
 * SummereSamling: Klientprogram
 *
 */

import java.util.*;
class Summerer {
  public double summer(ArrayList<? extends Number> liste) {
    double sum = 0;
    for (Number n : liste) {
      sum += n.doubleValue();
    }
    return sum;
  }
}

class SummereSamling {
  public static void main(String[] args) {
    ArrayList<Float> floatListe = new ArrayList<Float>();
    ArrayList<Integer> intListe = new ArrayList<Integer>();
    Summerer summerer = new Summerer();

    floatListe.add(new Float(10.0));
    floatListe.add(new Float(23.6));
    floatListe.add(new Float(3));

    intListe.add(new Integer(67));
    intListe.add(new Integer(987));
    intListe.add(new Integer(-1678));

    /* Summerer de to listene med det samme kallet til summer():  */
    System.out.println("Summen av elementene i float-listen er: "
                               + summerer.summer(floatListe));
    System.out.println("Summen av elementene i integer-listen er: "
                               + summerer.summer(intListe));
  }
}
/*Utskrift:
Summen av elementene i float-listen er: 36.60000038146973
Summen av elementene i integer-listen er: -624.0
*/