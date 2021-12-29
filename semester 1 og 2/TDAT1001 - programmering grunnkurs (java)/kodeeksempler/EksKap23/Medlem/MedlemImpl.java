/**
 * MedlemImpl.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder klassene MedlemImpl og MedlemsfabrikkImpl.
 *
 * Klassen MedlemImpl er mutabel. Alle data kan endres.
 * Et objekt best�r av et personobjekt og et strengobjekt.
 * Klassen Person er mutabel, her brukes den i parameterlister og
 * som returtype. I og med at overf�ringen skjer via serialisering, er
 * det imidlertid ingen fare for utilsiktede endringer i dette objektet
 * fra klientens side. Vi forutsetter at tjenerprogrammet heller
 * ikke endrer personobjektet. Vi trenger derfor ikke beskytte objektet
 * ved � lage egne kopier av det.
 */

import java.rmi.*;
import java.rmi.server.*;
import mittBibliotek.Person;

class MedlemImpl extends UnicastRemoteObject implements Medlem {
  private Person person;
  private String adresse;

  public MedlemImpl(Person person, String adresse) throws RemoteException {
    this.person = person;
    this.adresse = adresse;
  }

  public synchronized Person getPerson() throws RemoteException {
    return person;
  }

  public synchronized void setPerson(Person person) throws RemoteException {
    this.person = person;
  }

  public synchronized String getAdresse() throws RemoteException {
    return adresse;
  }

  public synchronized void setAdresse(String adresse) throws RemoteException {
    this.adresse = adresse;
  }
}

/**
 * Klassen MedlemsfabrikkImpl gj�r det mulig for en klient
 * � lage et objekt her p� tjenersiden.
 */
class MedlemsfabrikkImpl
         extends UnicastRemoteObject implements Medlemsfabrikk {

  public MedlemsfabrikkImpl() throws RemoteException {
  }

  public Medlem lagMedlem(Person person, String adresse)
                                    throws RemoteException {
    return new MedlemImpl(person, adresse);
  }
}