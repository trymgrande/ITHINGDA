/**
 * JaNeiTellerImpl.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Implementerer interfacet JaNeiTeller.
 * Et objekt av klassen teller opp ja- og nei-stemmer.
 * Alle metodene er synkronisert slik at flere kan aksessere objektet av gangen.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class JaNeiTellerImpl extends UnicastRemoteObject implements JaNeiTeller {
  private int antallJa = 0;
  private int antallNei = 0;

  /* Exception krever at vi m� lage konstrukt�r selv om den er tom */
  public JaNeiTellerImpl() throws RemoteException {
  }

  public synchronized void �kAntallJa() throws RemoteException {
    System.out.println("N� ble antall ja-stemmer �kt med 1");
    antallJa++;
  }

  public synchronized void �kAntallNei() throws RemoteException {
    System.out.println("N� ble antall nei-stemmer �kt med 1");
    antallNei++;
  }

  public synchronized void �kAntallJa(int �kning) throws RemoteException {
    System.out.println("N� ble antall ja-stemmer �kt med " + �kning);
    antallJa += �kning;
  }

  public synchronized void �kAntallNei(int �kning) throws RemoteException {
    System.out.println("N� ble antall nei-stemmer �kt med " + �kning);
    antallNei += �kning;
  }

  public synchronized int getAntallJa() throws RemoteException {
    return antallJa;
  }

  public synchronized int getAntallNei() throws RemoteException {
    return antallNei;
  }
}