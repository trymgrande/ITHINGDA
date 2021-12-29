/*
 * Tekstskur.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Demonstrasjon av kommunikasjon mellom tr�der gjennom at tr�dene venter
 * med � skrive ut tekst til bruker har tastet inn teksten.
 *
 * Filen inneholder tre klasser:
 * Skriver: Har ansvaret for � skrive en tekst.
 *             Har ogs� metode for � lese inn tekst.
 * Tekstskriver: Tr�d som benytter Skriver til � skrive tekst.
 * Tekstskur: Hovedprogram. Et antall tr�der lages, og en av skriverne
 *      bes om � lese inn tekst fra bruker.
 */

import static javax.swing.JOptionPane.*;

/**
 * Objekter av denne klassen kan skrive ut en gitt tekst.
 */
class Skriver {
  private String tekstSomSkrives = "";
  public synchronized void nyTekstFraBruker() {
    tekstSomSkrives = showInputDialog("Skriv inn ny tekst: ");
    if (tekstSomSkrives != null && !tekstSomSkrives.equals("")) {
      notifyAll();
    }
  }

  /**
   * Metoden skriver ut objektets tekst dersom den ikke er
   * tom. Er den det, kalles wait(), for � vente p� at en annen
   * tr�d f�r lagt inn en tekst.
   */
  public synchronized void skrivTekst() {
    while (tekstSomSkrives.equals("")) {
      try {
        wait();
      }
      catch (InterruptedException e) {
      }
    }
    System.out.print("Tekst er: ");
    System.out.println(tekstSomSkrives);
    notifyAll(); // Ferdig, kan vekke de andre.
  }
}

class Tekstskriver extends Thread {
  private Skriver skriveren;
  public Tekstskriver(Skriver skriveren) {
    this.skriveren = skriveren;
  }
  public void run() {
    while (true) {
      skriveren.skrivTekst();
    }
  }
}

class Tekstskur {
  public static void main(String[] args) {
    Skriver enSkriver = new Skriver();
    Tekstskriver skriver1 = new Tekstskriver(enSkriver);
    Tekstskriver skriver2 = new Tekstskriver(enSkriver);
    Tekstskriver skriver3 = new Tekstskriver(enSkriver);
    Tekstskriver skriver4 = new Tekstskriver(enSkriver);
    Tekstskriver skriver5 = new Tekstskriver(enSkriver);
    skriver1.start();
    skriver2.start();
    skriver3.start();
    skriver4.start();
    skriver5.start();
    enSkriver.nyTekstFraBruker();
  }
}

/* Utskrift fra dette programmet blir ingenting helt til brukeren har
 * skrevet en tekst i dialogvinduet. Deretter skrives denne teksten om
 * og om igjen.
 */