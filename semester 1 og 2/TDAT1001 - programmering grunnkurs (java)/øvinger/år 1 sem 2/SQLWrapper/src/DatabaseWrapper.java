import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseWrapper {
    public static void main(String[] args) throws Exception{
        System.out.println("starter opp..");
        Scanner s = new Scanner(System.in);
        String password = "g7YoBeEm";

        String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/trymg?user=trymg&password=" + password;
        Connection con = DriverManager.getConnection(url);

        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("SELECT * FROM boktittel");

        while (res.next()) {
            System.out.println("Personnr = " + res.getInt("persnr"));
            System.out.println("Fornavn = " + res.getString("fornavn"));
            System.out.println("Etternavn = " + res.getString("etternavn"));
        }
        con.close();
    }
}