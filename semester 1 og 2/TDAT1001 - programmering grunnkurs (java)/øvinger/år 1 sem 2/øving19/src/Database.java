import javax.xml.transform.Result;
import java.sql.*;

public class Database {
    String password = "g7YoBeEm";

    private String databaseURL = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/trymg?user=trymg&password=" + password;
    private Connection con;

    Database() {
        try {
            con = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            System.out.println("could not open connection:");
            //e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public void lukkForbindelse() {
        if (con != null) {
            try {
                con.close();
                System.out.println("connection closed");
            } catch (SQLException e) {
                System.out.println("could not close connection:");
                //e.printStackTrace();
            }
        }
    }
    public boolean regNyBok(Bok nyBok) {
        try {
            con.setAutoCommit(false);
            //sql-setninger
            PreparedStatement selectSetning;
            PreparedStatement insertSetning;

            String stat1 = "insert into boktittel(isbn, forfatter, tittel) values(?, ?, ?);";
            insertSetning = con.prepareStatement(stat1);
            insertSetning.setString(1, nyBok.getIsbn());
            insertSetning.setString(2, nyBok.getForfatter());
            insertSetning.setString(3, nyBok.getTittel());
            insertSetning.execute();
            String stat2 = "insert into eksemplar(isbn, eks_nr) values (?, 1);";
            insertSetning = con.prepareStatement(stat2);
            insertSetning.setString(1, nyBok.getIsbn());
            insertSetning.execute();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            System.out.println("sql-setninger feilet, ruller tilbake:");
            //e.printStackTrace();
            rullTilbake(con);
            return false;
        }
        finally {
            settAutoCommit(con);
        }
    }
    public void settAutoCommit(Connection con) {
        try {
            if (con != null && !con.getAutoCommit()) {
                con.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            System.out.println("setAutoCommit failed:");
            //e.printStackTrace();
        }
    }

    public static void rullTilbake(Connection con) {
        try {
            if (con != null && !con.getAutoCommit()) {
                con.rollback();
            }
        }
        catch (SQLException e) {
            System.out.println("fatal: rollback failed:");
            //e.printStackTrace();
        }
    }

    public int regNyttEksemplar(String isbn) {
        try {
            con.setAutoCommit(false);
            PreparedStatement selectSetning;
            PreparedStatement insertSetning;
            ResultSet res;
            //sql-setninger
            //finner høyeste eks_nr
            String statHøyesteEks_nr = "SELECT max(eks_nr) as max_nr from eksemplar where isbn = ?;";
            selectSetning = con.prepareStatement(statHøyesteEks_nr);
            selectSetning.setString(1, isbn);
            res = selectSetning.executeQuery();
            res.next();
            int max_nr = res.getInt("max_nr");
            String stat1 = "insert into eksemplar(isbn, eks_nr) values (?, ?);";
            insertSetning = con.prepareStatement(stat1);
            insertSetning.setString(1, isbn);
            insertSetning.setInt(2, max_nr+1);
            int res2 = insertSetning.executeUpdate();
            if (res2 == 0) {
                System.out.println("invalid input data");
                rullTilbake(con);
                return 0;
            }
            con.commit();
            return max_nr+1;
        }
        catch (SQLException e) {
            System.out.println("sql-setninger feilet, ruller tilbake:");
            //e.printStackTrace();
            rullTilbake(con);
            return 0;
        }
        finally {
            settAutoCommit(con);
        }
    }
    public boolean lånUtEksemplar(String isbn, String navn, int eksNr) {
        try {
            con.setAutoCommit(false);
            //sql-setninger
            PreparedStatement selectSetning;
            PreparedStatement insertSetning;
            String stat1 = "update eksemplar set laant_av = ? where isbn = ? and eks_nr = ?";
            insertSetning = con.prepareStatement(stat1);
            insertSetning.setString(1, navn);
            insertSetning.setString(2, isbn);
            insertSetning.setInt(3, eksNr);
            //if (insert returner 0)
            int res = insertSetning.executeUpdate();
            if (res == 0) {
                System.out.println("invalid input data");
                rullTilbake(con);
                return false;
            }
            con.commit();
            return true;
        }
        catch (SQLException e) {
            System.out.println("sql-setninger feilet, ruller tilbake:");
            //e.printStackTrace();
            rullTilbake(con);
            return false;
        }
        finally {
            settAutoCommit(con);
        }
    }
}

//hvor skal return være ift. try/catch?

if (timeliste <= 135) {
    karakter = 'C';
}