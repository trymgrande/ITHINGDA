/*
 * TellemaskinFront.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dette er det grensesnittet som tjeneren tilbyr klientene.
 * For mer info. se implementasjonen TellemaskinFrontImpl.java.
 */

import java.rmi.*;
interface TellemaskinFront extends Remote {
  void registrerMeg(Klient klienten) throws RemoteException;
  void meldMegUt(Klient klienten) throws RemoteException;
  void �kAntallJa() throws RemoteException;
  void �kAntallNei() throws RemoteException;
  void �kAntallJa(int �kning) throws RemoteException;
  void �kAntallNei(int �kning) throws RemoteException;
  int getAntallJa() throws RemoteException;
  int getAntallNei() throws RemoteException;
}