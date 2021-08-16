/**
 * Klient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Tellemaskinen henvender seg til et objekt med dette interfacet når den skal varsle
 * alle klienter om endringer i dataene.
 */

import java.rmi.*;
interface Klient extends Remote {
  String getNavn() throws RemoteException;
  void skrivStatus(String[] status) throws RemoteException;  // tre statuslinjer
}

