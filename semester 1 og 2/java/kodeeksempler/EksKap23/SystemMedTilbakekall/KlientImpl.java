/**
 * KlientImpl.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Implementerer interfacet Klient
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KlientImpl extends UnicastRemoteObject implements Klient {
  private String navn;
  private TelleVindu vindu;

  public KlientImpl(String navn, TelleVindu vindu) throws RemoteException {
    this.vindu = vindu;
    this.navn = navn;
  }

  public synchronized String getNavn() throws RemoteException {
    return navn;
  }

  public synchronized void skrivStatus(String[] status) throws RemoteException {
    vindu.setStatus(status);
  }
}

