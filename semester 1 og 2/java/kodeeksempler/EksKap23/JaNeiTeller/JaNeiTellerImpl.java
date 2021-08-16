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

  /* Exception krever at vi må lage konstruktør selv om den er tom */
  public JaNeiTellerImpl() throws RemoteException {
  }

  public synchronized void økAntallJa() throws RemoteException {
    System.out.println("Nå ble antall ja-stemmer økt med 1");
    antallJa++;
  }

  public synchronized void økAntallNei() throws RemoteException {
    System.out.println("Nå ble antall nei-stemmer økt med 1");
    antallNei++;
  }

  public synchronized void økAntallJa(int økning) throws RemoteException {
    System.out.println("Nå ble antall ja-stemmer økt med " + økning);
    antallJa += økning;
  }

  public synchronized void økAntallNei(int økning) throws RemoteException {
    System.out.println("Nå ble antall nei-stemmer økt med " + økning);
    antallNei += økning;
  }

  public synchronized int getAntallJa() throws RemoteException {
    return antallJa;
  }

  public synchronized int getAntallNei() throws RemoteException {
    return antallNei;
  }
}