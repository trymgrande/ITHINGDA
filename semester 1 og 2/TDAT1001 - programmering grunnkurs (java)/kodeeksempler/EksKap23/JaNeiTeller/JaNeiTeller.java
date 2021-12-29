/**
 * JaNeiTeller.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Interface for tellemaskin, se figur 23.3, side 783.
 *
 */
import java.rmi.*;
interface JaNeiTeller extends Remote {
  void �kAntallJa() throws RemoteException;
  void �kAntallNei() throws RemoteException;
  void �kAntallJa(int �kning) throws RemoteException;
  void �kAntallNei(int �kning) throws RemoteException;
  int getAntallJa() throws RemoteException;
  int getAntallNei() throws RemoteException;
}