/**
 * HashMapKlient.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Demonstrasjon av HashMap.
 * En klasse Ident benyttes som nøkkelverdi.
 * Nøkkelverdiene er vist på figur 21.12, side 739.
 *
 * Filen inneholder to klasser:
 * Ident: Klasse som utgjør nøkkelverdi
 * HashMapKlient: Program som gjør operasjoner på en hashtabell
 *
 */
import java.util.*;

/**
 * Klassen Ident beskriver hashfunksjonen som er brukt på figur 21.12.
 */
class Ident {
  private int verdi;
  public Ident(int verdi) {
    this.verdi = verdi;
  }

  /**
   * Plukker ut siste siffer i tallverdien
   */
  public int hashCode() {  // omdefinerer hashCode() slik den er arvet fra Object
    int sisteSiffer = verdi % 10;
    System.out.println("Verdi " + verdi + " gir kode " + sisteSiffer);
    return sisteSiffer;
  }

  public String toString() {
    return "" + verdi;
  }

  public boolean equals(java.lang.Object obj) {
    return ((Ident) obj).verdi == verdi;
  }
}

class HashMapKlient {
  public static void main(String[] args) {
    HashMap<Ident, String> hashEksempel =
                new HashMap<Ident, String>(10);  // for å passe med figuren
    hashEksempel.put(new Ident(2), "Ole");
    hashEksempel.put(new Ident(11), "Ingrid");
    hashEksempel.put(new Ident(16), "Åse");
    hashEksempel.put(new Ident(18), "Beate");
    hashEksempel.put(new Ident(31), "Tove");
    hashEksempel.put(new Ident(35), "Petter");
    hashEksempel.put(new Ident(55), "Anders");
    hashEksempel.put(new Ident(85), "Toril");
    hashEksempel.put(new Ident(94), "Tomas");
    hashEksempel.put(new Ident(108), "Svein");

    System.out.println("Innholdet i hashtabellen: " + hashEksempel.toString());
    String test = hashEksempel.get(new Ident(11));
    if (test == null ) {
      System.out.println("11 finnes ikke");
    } else {
      System.out.println("11 finnes: " + test);
    }

    test = hashEksempel.get(new Ident(6));
    if (test == null ) {
      System.out.println("6 finnes ikke");
    } else {
      System.out.println("6 finnes: " + test);
    }
  }
}

/*
Utskrift:
Verdi 2 gir kode 2
Verdi 11 gir kode 1
Verdi 16 gir kode 6
Verdi 18 gir kode 8
Verdi 31 gir kode 1
Verdi 35 gir kode 5
Verdi 55 gir kode 5
Verdi 85 gir kode 5
Verdi 94 gir kode 4
Verdi 108 gir kode 8
Innholdet i hashtabellen: {31=Tove, 11=Ingrid, 2=Ole, 94=Tomas,
85=Toril, 55=Anders, 35=Petter, 16=Åse, 108=Svein, 18=Beate}
Verdi 11 gir kode 1
11 finnes: Ingrid
Verdi 6 gir kode 6
6 finnes ikke
*/
