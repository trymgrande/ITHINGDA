/*
 * TellemaskinFrontImpl.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er implementasjonen av "fronten" mot tellemaskinen p� tjenersiden.
 * Klientene kommuniserer med et objekt av denne klassen. Dette objektet
 * sender meldinger videre til et objekt av klassen JaNeiTeller fra programliste 23.2, side 784.
 * Klassen JaNeiTeller er ikke kjent for klientene.
 */

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

class TellemaskinFrontImpl
    extends UnicastRemoteObject implements TellemaskinFront {
  /*
   * Et objekt av klassen best�r av et objekt av klassen JaNeiTeller og et objekt av
   * klassen ArrayList. Disse objektene brukes bare internt i denne klassen.
   * Det er derfor ikke n�dvendig � beskytte dem vha. synchronized. De er
   * beskyttet p� grunn av at alle metodene i klassen TellemaskinFront er
   * synkronisert.(ArrayList er ikke synkronisert. Se online API-dok. dersom du
   * trenger � synkronisere en ArrayList.)
   */
  private JaNeiTeller tellemaskinen = new JaNeiTeller();
  private ArrayList<Klient> klientene = new ArrayList<Klient>();

  public TellemaskinFrontImpl() throws RemoteException {
  }

  /* Registrerer en ny klient */
  public synchronized void registrerMeg(Klient klienten) throws RemoteException {
    try {
      klientene.add(klienten);
      System.out.println("N� er " + klienten.getNavn() + " registrert.");
      klienten.skrivStatus(lagMelding());
    } catch (Exception e) {
      System.out.println("Feil oppst�tt i registrerMeg(): " + e);
      e.printStackTrace();
    }
  }

/* Melder ut en klient. Ingenting skjer dersom klienten ikke eksisterer. */
public synchronized void meldMegUt(Klient klienten) throws RemoteException {
  boolean funnet = false;
  int klientIndeks = 0;
  while (klientIndeks < klientene.size() && !funnet) {
    Klient denne = klientene.get(klientIndeks);
    if (denne.equals(klienten)) {  // bruker equals() for � sammenlikne stubbobjektene
      funnet = true;
      klientene.remove(klientIndeks);
      System.out.println("N� er klienten " + klienten.getNavn() + " fjernet.");
    } else klientIndeks++;
  }
}

  public synchronized void �kAntallJa() throws RemoteException {
    System.out.println("N� ble antall ja-stemmer �kt med 1");
    tellemaskinen.�kAntallJa();
    varsleAlle();
  }

  public synchronized void �kAntallNei() throws RemoteException {
    System.out.println("N� ble antall nei-stemmer �kt med 1");
    tellemaskinen.�kAntallNei();
    varsleAlle();
  }

  public synchronized void �kAntallJa(int �kning) throws RemoteException {
    tellemaskinen.�kAntallJa(�kning);
    System.out.println("N� ble antall ja-stemmer �kt med " + �kning + ".");
    varsleAlle();
  }

  public synchronized void �kAntallNei(int �kning) throws RemoteException {
    tellemaskinen.�kAntallNei(�kning);
    System.out.println("N� ble antall nei-stemmer �kt med " + �kning + ".");
    varsleAlle();
  }

  public synchronized int getAntallJa() throws RemoteException {
    return tellemaskinen.getAntallJa();
  }

  public synchronized int getAntallNei() throws RemoteException {
    return tellemaskinen.getAntallNei();
  }

  private synchronized String[] lagMelding() {
    java.util.Date n� = new java.util.Date(); // akkurat n�
    java.text.DateFormat tidsformat =
                         java.text.DateFormat.getTimeInstance();
    String[] status = new String[3];
    status[0] = "Klokka er " + tidsformat.format(n�);
    status[1] = "Antall ja-stemmer lik " + tellemaskinen.getAntallJa();
    status[2] = "Antall nei-stemmer er lik " + tellemaskinen.getAntallNei();
    return status;
  }

  private synchronized void varsleAlle() throws RemoteException {
    System.out.println("Skal varsle alle om endringen.");
    String[] melding = lagMelding();
    int klientIndeks = 0;
    while (klientIndeks < klientene.size()){
      Klient denne = klientene.get(klientIndeks);
      try {
        denne.skrivStatus(melding);
        klientIndeks++; // oppdaterer indeks bare dersom vi har f�tt kontakt
      } catch (ConnectException e) {  // klienten er koblet ned
        System.out.println(
          "F�r ikke kontakt med klient med indeks " + klientIndeks + ": " + e);
        /*
         * Vi fjerner klienten p� den aktuelle indeksen. Det betyr at den bak rykker
         * en posisjon fram. Vi oppdaterer derfor ikke klientIndeks i dette tilfellet.
         */
        klientene.remove(klientIndeks);
        System.out.println("N� er vedkommende fjernet fra listen. Vi fortsetter ...");
      }
    }
  }
}