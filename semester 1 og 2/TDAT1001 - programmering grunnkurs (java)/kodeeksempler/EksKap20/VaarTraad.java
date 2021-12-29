/**
 * VaarTraad.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Demonstrasjon av tr�der med Runnable
 *
 * Filen inneholder tre klasser:
 * Superklasse: En eller annen klasse
 * Traaden: Subklasse til Superklasse, men likevel tr�d
 * VaarTraad: Program som lager en tr�d av Traaden
 */

class Superklasse {
}

class Traaden extends Superklasse implements Runnable {
  private Thread traad;
  public Traaden() {
    traad = new Thread(this);
    traad.start();
  }

  public void run() {
    while (true) {
      System.out.println("Lever ...");
    }
  }
}

class VaarTraad {
  public static void main(String[] args) {
    Traaden tr = new Traaden();
  }
}
