// Nederst side 466 - �verst side 467
import pakkeA.TestKlasse1;
class Test {
  public static void main(String[] args) {
    //TestKlasse1 objekt1 = new TestKlasse1(10); // ikke ok, beskyttet konstrukt�r
    TestKlasse1 objekt1 = new TestKlasse1(); // ok
    TestKlasse2 objekt2 = new TestKlasse2(20); // ok,
    //objekt2.metode1(); // ikke ok, beskyttet
    objekt2.metode2(objekt1, objekt2); // ok
  }
}