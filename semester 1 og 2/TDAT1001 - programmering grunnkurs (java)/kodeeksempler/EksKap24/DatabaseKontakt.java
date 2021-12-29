/**
 * DatabaseKontakt.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet kopler seg til en database, og henter ut innholdet i tabellen Person.
 * SQL-skript for å lage tabellen Person er gjengitt nederst i denne filen.
 *
 * Stien til databasedriveren er satt opp i CLASSPATH. Dersom driveren er en
 * zip- eller jar-fil, må både sti og filnavn inn i CLASSPATH.
 */

import static javax.swing.JOptionPane.*;
import java.sql.*;
class DatabaseKontakt {
  public static void main(String[] args) throws Exception {

    /* Hvis du bruker en annen driver, må du skifte ut strengkonstanten i neste linje. */
    String databasedriver = "org.apache.derby.jdbc.ClientDriver";
    Class.forName(databasedriver);  // laster inn driverklassen

    /*
     * Vår database heter persondata, og den kjører på samme maskin som
     * dette klientprogrammet. Strengkonstanten i neste linje må du
     * tilpasse din situasjon.
     */
    String databasenavn
      = "jdbc:derby://localhost:1527/persondata;user=vprg;password=vprg";
    Connection forbindelse = DriverManager.getConnection(databasenavn);
    Statement setning = forbindelse.createStatement();
    ResultSet res = setning.executeQuery("select * from person");
    while (res.next()) {
      int persNr = res.getInt("persnr");
      String fornavn = res.getString("fornavn");
      String etternavn = res.getString("etternavn");
      System.out.println(persNr + ": " + fornavn + " " + etternavn);
    }
    res.close();
    setning.close();
    forbindelse.close();
  }
}

/*
SQL-skript for å lage tabellen Person:

create table person(
 persnr    integer primary key,
 fornavn   varchar(30) not null,
 etternavn varchar(30) not null);

insert into person values (100, 'Ole', 'Hansen');
insert into person values (101, 'Anne Grethe', 'Ås');
insert into person values (102, 'Jonny', 'Hansen');

Utskrift fra programmet:

100: Ole Hansen
101: Anne Grethe Ås
102: Jonny Hansen
*/