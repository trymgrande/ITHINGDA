/**
 * JaNeiTeller.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Interface for tellemaskin, se figur 23.3, side 783.
 *
 */
import java.rmi.*;
interface JaNeiTeller extends Remote {
  void økAntallJa() throws RemoteException;
  void økAntallNei() throws RemoteException;
  void økAntallJa(int økning) throws RemoteException;
  void økAntallNei(int økning) throws RemoteException;
  int getAntallJa() throws RemoteException;
  int getAntallNei() throws RemoteException;
}