/**
 * EksempelInterface.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder et interface og en klasse som implementerer dette,
 * samt et lite klientprogram
 */

interface Sammenligningsbar<Type> {
  /* public abstract er underforstått for metoder */
  boolean størreEnn(Type obj);
  boolean mindreEnn(Type obj);
  boolean lik(Type obj);
}

class FireSifretHeltall implements Sammenligningsbar<FireSifretHeltall> {
  private static final int MIN = 1000;
  private static final int MAKS = 9999;
  private final int verdi;

  public FireSifretHeltall(int startVerdi) {
    if (startVerdi < MIN) {
      verdi = MIN;
    } else if (startVerdi > MAKS) {
        verdi = MAKS;
    } else {
      verdi = startVerdi;
    }
  }

  public int getVerdi() {
    return verdi;
  }

  public boolean størreEnn(FireSifretHeltall tall) {
    return (verdi > tall.getVerdi());
  }

  public boolean mindreEnn(FireSifretHeltall tall) {
    return (verdi < tall.getVerdi());
  }

  public boolean lik(FireSifretHeltall tall) {
    return (verdi == tall.getVerdi());
  }
}

class EksempelInterface {
  public static void main(String[] args) {
    FireSifretHeltall tall1 = new FireSifretHeltall(700);
    FireSifretHeltall tall2 = new FireSifretHeltall(1700);
    FireSifretHeltall tall3 = new FireSifretHeltall(70000);
    System.out.println(tall1.getVerdi());
    System.out.println(tall2.getVerdi());
    System.out.println(tall3.getVerdi());
    System.out.println(tall1.størreEnn(tall2));
    System.out.println(tall1.mindreEnn(tall2));
    System.out.println(tall1.lik(tall2));
  }
}

/* Utskrift:
1000
1700
9999
false
true
false
*/
