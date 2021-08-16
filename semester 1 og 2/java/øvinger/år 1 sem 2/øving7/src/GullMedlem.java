import java.time.LocalDate;

public class GullMedlem extends BonusMedlem {
    public GullMedlem(int medlemsnummer, Personalia pers, LocalDate innmeldt, int poeng) {
        super(medlemsnummer, pers, innmeldt, poeng);
    }

    @Override
    public void registrerPoeng(int poeng) {
        setPoeng((int) (poeng * 1.5));
    }
}