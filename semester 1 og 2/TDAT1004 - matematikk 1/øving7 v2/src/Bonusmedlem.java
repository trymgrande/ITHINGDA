import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Bonusmedlem {
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private int poeng = 0;

    public Bonusmedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = LocalDate.now();
    }

    public int getMedlnr() {
        return medlNr;
    }

    public Personalia getPersonalia() {
        return pers;
    }

    public LocalDate getInnmeldt() {
        return innmeldtDato;
    }

    public int getPoeng() {
        return poeng;
    }

    public int finnKvalPoeng(LocalDate innmeldtDato) {
        int dagerMellom = Period.between(innmeldtDato, LocalDate.now()).getDays();
        if (dagerMellom > 365) {
            return 0;
        }
        else {
            return dagerMellom;
        }
    }

    public boolean okPassord(String nyttPassord) {
        if (nyttPassord.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean registrerPoeng(int nyePoeng) {
        this.poeng += nyePoeng;
        System.out.println("poeng registrert, saldo: " + this.poeng);
        return true;
    }
}
