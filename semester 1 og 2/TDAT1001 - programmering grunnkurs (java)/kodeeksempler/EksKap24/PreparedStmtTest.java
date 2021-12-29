/**
 * PreparedStmtTest.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser eksempel p� bruk av ferdigkompilerte SQL-setninger
 */

import static javax.swing.JOptionPane.*;
import java.sql.*;
import mittBibliotek.Opprydder;
class PreparedStmtTest {
  public static void main(String[] args) throws Exception {
    String databasedriver = "org.apache.derby.jdbc.ClientDriver";
    Class.forName(databasedriver);
    String dbnavn
              = "jdbc:derby://localhost:1527/persondata;user=vprg;password=vprg";
    Connection forbindelse =  null;
    PreparedStatement setning = null;
    ResultSet res = null;
    try {
      forbindelse = DriverManager.getConnection(dbnavn);
      String sqlSetning
                 =  "select * from person where fornavn like ? and etternavn like ?";
      setning = forbindelse.prepareStatement(sqlSetning);

      String kritForn = showInputDialog(
                              "S�kekriterium fornavn (avslutt med trykk Escape):");
      while (kritForn != null) {
        String kritEttern = showInputDialog("S�kekriterium etternavn: ");
        setning.setString(1, kritForn.trim());
        setning.setString(2, kritEttern.trim());
        System.out.println("\nS�kekriteriene er " + kritForn + " " + kritEttern + ".");
        res = setning.executeQuery();
        while (res.next()) {
          int nr = res.getInt("persnr");
          String fornavn = res.getString("fornavn");
          String etternavn = res.getString("etternavn");
          System.out.println(fornavn + " " + etternavn);
        }
        res.close();
        kritForn = showInputDialog(
                      "S�kekriterium fornavn (avslutt med trykk Escape):");
      }
    } catch (SQLException e) {
      Opprydder.skrivMelding(e, "Ruller tilbake");
    } finally {
      Opprydder.lukkResSet(res);
      Opprydder.lukkSetning(setning);
      Opprydder.lukkForbindelse(forbindelse);
    }
  }
}

/* Utskrift fra programmet:

S�kekriteriene er % H%.
Ole Hansen
Jonny Hansen

S�kekriteriene er % %.
Ole Hansen
Anne Grethe �s
Jonny Hansen

S�kekriteriene er ___ H%.
Ole Hansen
*/