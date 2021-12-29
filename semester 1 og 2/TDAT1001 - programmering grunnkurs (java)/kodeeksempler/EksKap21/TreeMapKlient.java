/*
 * TreeMapKlient.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Demonstrasjon av TreeMap.
 * Nøkkelverdiene er nodene i treet i figur 21.11.
 * Dataverdiene er vilkårlige navn.
 *
 */
import java.util.*;
class TreeMapKlient {
  public static void main(String[] args) {
    TreeMap<Integer, String> treEksempel = new TreeMap<Integer, String>();
    treEksempel.put(new Integer(15), "Ole");
    treEksempel.put(new Integer(10), "Ingrid");
    treEksempel.put(new Integer(28), "Åse");
    treEksempel.put(new Integer(3), "Beate");
    treEksempel.put(new Integer(14), "Tove");
    treEksempel.put(new Integer(20), "Petter");
    treEksempel.put(new Integer(16), "Anders");
    treEksempel.put(new Integer(21), "Toril");

    System.out.println("Innholdet i treet: " + treEksempel.toString());
    String test = (String) treEksempel.get(new Integer(11));
    if (test == null ) {
      System.out.println("11 finnes ikke");
    } else {
      System.out.println("11 finnes: " + test);
    }

    test = (String) treEksempel.get(new Integer(10));
    if (test == null ) {
      System.out.println("10 finnes ikke");
    } else {
      System.out.println("10 finnes: " + test);
    }

    System.out.println("Laveste nøkkelverdi: " + treEksempel.firstKey());
    System.out.println("Høyeste nøkkelverdi: " + treEksempel.lastKey());
  }
}

/* Utskrift:
Innholdet i treet: {3=Beate, 10=Ingrid, 14=Tove, 15=Ole, 16=Anders, 20=Petter,
21=Toril, 28=Åse}
11 finnes ikke
10 finnes: Ingrid
Laveste nøkkelverdi: 3
Høyeste nøkkelverdi: 28
*/
