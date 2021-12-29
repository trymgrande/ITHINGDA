/**
 * TallskurSynk.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Eksemplet demonstrerer synkronisering gjennom å sikre at tråder
 * skriver en rekke tall i rett rekkefølge.
 * Prøv programmet med og uten synchronized.
 *
 * Filen inneholder tre klasser:
 * SekvensSkriver: Et objekt som skriver en tallsekvens
 * Tallskriver: Tråd som benytter en SekvensSkriver
 * TallskurSynk: Programmet som lager og kjører trådene
 */

class SekvensSkriver {
  public void skrivSekvens() {  // ikke synchronized
  //public synchronized void skrivSekvens() {
    try {
      System.out.print("1 ");
      Thread.sleep(10); // forsinkelse for å illustrere effekten
      System.out.print("2 ");
      Thread.sleep(10);
      System.out.print("3 ");
      Thread.sleep(10);
      System.out.print("4 ");
      Thread.sleep(10);
      System.out.print("5 ");
    } catch (InterruptedException e) {
    }
  }
}

class Tallskriver extends Thread {
  private SekvensSkriver skriver;
  public Tallskriver(SekvensSkriver skriver) {
    this.skriver = skriver;
  }
  public void run() {
    while (true) {
      skriver.skrivSekvens();
    }
  }
}

class TallskurSynk {
  public static void main(String[] args) {
    SekvensSkriver enSkriver = new SekvensSkriver();
    Tallskriver skriver1 = new Tallskriver(enSkriver);
    Tallskriver skriver2 = new Tallskriver(enSkriver);
    Tallskriver skriver3 = new Tallskriver(enSkriver);
    Tallskriver skriver4 = new Tallskriver(enSkriver);
    Tallskriver skriver5 = new Tallskriver(enSkriver);
    skriver1.start();
    skriver2.start();
    skriver3.start();
    skriver4.start();
    skriver5.start();
  }
}


