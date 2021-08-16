// Midt på side 466
import pakkeA.*;
class TestKlasse2 extends TestKlasse1 {
  public TestKlasse2(int a) {
    super(a); // ok, protected
  }
  public void metode2(TestKlasse1 obj1, TestKlasse2 obj2) {
    metode1(); // ok, protected
    //obj1.metode1(); // ikke ok
    obj2.metode1(); // ok
  }
}