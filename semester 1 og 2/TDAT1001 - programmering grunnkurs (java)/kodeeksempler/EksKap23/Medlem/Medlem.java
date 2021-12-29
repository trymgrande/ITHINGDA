/**
 * Medlem.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Et interface til et medlemsobjekt. Bruker klassen Person i mittBibliotek.
 */

import java.rmi.*;
import mittBibliotek.Person;

interface Medlem extends Remote {
  Person getPerson() throws RemoteException;
  void setPerson(Person nyPerson) throws RemoteException;
  String getAdresse() throws RemoteException;
  void setAdresse(String nyAdresse) throws RemoteException;
}

interface Medlemsfabrikk extends Remote {
  Medlem lagMedlem(Person person, String adresse) throws RemoteException;
}