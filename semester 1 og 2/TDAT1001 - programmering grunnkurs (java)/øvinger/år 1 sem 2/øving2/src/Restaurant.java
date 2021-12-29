import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Restaurant {
    private String navn;
    private final int ETABLERINGSÅR;
    private Bord bord;

    public Restaurant(String navn, int etableringsår, int antallBord) {
        this.navn = navn;
        this.ETABLERINGSÅR = etableringsår;
        bord = new Bord(antallBord);
    }
    public String getNavn() {
        return navn;
    }
    public Bord getBord() {
        return bord;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    public int getETABLERINGSÅR() {
        return ETABLERINGSÅR;
    }
    public int getAlder() {
        LocalDate today = LocalDate.now();
        Period alder = Period.between(today, LocalDate.ofYearDay(ETABLERINGSÅR, 1));
        return Math.abs(alder.getYears());
    }
    public int getAntallLedigeBord() {
        return bord.getAntallLedigeBord();
    }
    public int getAntallOpptattBord() {
        return bord.getAntallBord() - bord.getAntallLedigeBord();
    }
    public int reserverBord(String navn, int antallBord) {
        for (int i = 0; i < antallBord; i++) {
            if (bord.reserverBord(navn) != 0) {
                return 1;
            }
        }
        return 0;
    }
    public int[] getBordReservertAv(String navn) {
        return bord.getBordReservertAv(navn);
    }
    public int frigiBord(int[] bordSomSkalFrigjøres) {
        return bord.frigiBord(bordSomSkalFrigjøres);
    }
}