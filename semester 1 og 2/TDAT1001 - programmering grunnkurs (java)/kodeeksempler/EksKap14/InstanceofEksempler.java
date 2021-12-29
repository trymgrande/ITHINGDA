/**
 * InstanceofEksempler.java   - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Bruk av instanceof der en melding kun kan sendes til en del av et subtre.
 * metode1() er abtrakt i klassen A og implementert i klassene B, C og D.
 * metode2() er abstrakt i klassen B og implementert i klassene E og F.
 */

abstract class A {
  public abstract void metode1();
}

abstract class B extends A {
  public void metode1() {
    System.out.println("B: metode1");
  }
  abstract void metode2();
}

class C extends A {
  public void metode1() {
    System.out.println("C: metode1");
  }
}

class D extends A {
  public void metode1() {
    System.out.println("D: metode1");
  }
}

class E extends B {
  public void metode2() {
    System.out.println("E: metode2");
  }
}

class F extends B {
  public void metode2() {
    System.out.println("F: metode2");
  }
}

class InstanceofEksempler {
  public static void main(String[] args) {
    A objekt1 = new C();
    A objekt2 = new E();
    objekt1.metode1();
    objekt2.metode1();
    if (objekt1 instanceof B) {
      B etObjekt = (B) objekt1;
      etObjekt.metode2();
    }
    if (objekt2 instanceof B) {
      B etObjekt = (B) objekt2;
      etObjekt.metode2();
    }
  }
}

/* Utskrift:
C: metode1
B: metode1
E: metode2
*/