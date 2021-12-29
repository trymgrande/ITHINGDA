import java.time.LocalDate;

public class BasicMedlem extends BonusMedlem {

    public BasicMedlem(int medlemsnummer, Personalia pers, LocalDate innmeldt) {
        super(medlemsnummer, pers, innmeldt, 0);
    }

    @Override
    public void registrerPoeng(int poeng) {
        setPoeng(poeng);
    }
}
