import java.sql.SQLException;

public class Testprogram {
    public static void main(String[] args) throws SQLException {

        Database db = new Database();
        Bok nyBok = new Bok("111", "bok2", "Trym");
        System.out.println(db.regNyBok(nyBok));
        System.out.println(db.regNyttEksemplar("111"));
        System.out.println(db.lånUtEksemplar("111", "trym", 1));
        db.lukkForbindelse();
    }
}