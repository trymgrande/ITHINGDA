import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.*;

abstract public class BonusMedlem {
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private int poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
        this.poeng = poeng;
    }

    public int getMedlNr() {
        return medlNr;
    }
    public Personalia getPersonalia() {
        return pers;
    }
    public LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }
    public int getPoeng() {
        return poeng;
    } //abstract
    public int finnKvalPoeng(LocalDate testdato) { //returnerer for lavt (resetter antakeligvis etter 365?)
        //skal returnere antall poeng som kan kvalifisere til oppgradering av medlemskapet til sølv eller gull.
        // Dersom innmeldingsdatoen  ligger mindre enn 365 dager bak i tid i forhold til datoen som sendes inn som argument,
        // returneres antall poeng. Hvis det er mer enn ett år siden kunden meldte seg inn, returneres 0 poeng.
        // Du kan finne differansen mellom to objekter av klasse LocalDate på denne måten:

        long dagerMellom = ChronoUnit.DAYS.between(innmeldtDato, testdato);
                Period.between(innmeldtDato, testdato).getDays();
        if (dagerMellom > 365) {
            return 0;
        }
        else {
            return poeng;
        }
    }
    public boolean okPassord(String passord) { //?
        return pers.okPassord(passord);
    }
    abstract public void registrerPoeng(int poeng);
    protected void setPoeng(int poeng) {
        this.poeng += poeng;

    }

}