/**
 * Tallskur.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Eksemplet demonstrerer trådbruk gjennom at mange tråder skriver et tall.
 *
 * Filen inneholder to klasser:
 * Tallskriver: Utgjør en tråd
 * Tallskur: Programmet som lager og kjører disse trådene
 */

class Tallskriver extends Thread {
  private int tall;
  public Tallskriver(int tall) {
    this.tall = tall;
  }
  public void run() {
    while (true) {
      System.out.print(tall);
      try {  // forsinkelse for å illustrere effekten
        Thread.sleep(10);  // kaster sjekket unnntak som må håndteres
      } catch (InterruptedException e) {  // se bort fra dette nå
      }
      System.out.print(" ");
    }
  }
}

class Tallskur {
  public static void main(String[] args) {
    Tallskriver skriver1 = new Tallskriver(1);
    Tallskriver skriver2 = new Tallskriver(2);
    Tallskriver skriver3 = new Tallskriver(3);
    Tallskriver skriver4 = new Tallskriver(4);
    Tallskriver skriver5 = new Tallskriver(5);
    skriver1.start();
    skriver2.start();
    skriver3.start();
    skriver4.start();
    skriver5.start();
  }
}

