public class Staa extends Tribune {
    private int antSolgteBilletter;

    public Staa(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {
        return antSolgteBilletter;
    }

    @Override
    public int finnInntekt() {
        return getPris() * antSolgteBilletter;
    }

    @Override
    public Billett[] kjøpBilletter(int antallBilletter) {
        if (antSolgteBilletter + antallBilletter >= getKapasitet()) {
            return null;
        }
        antSolgteBilletter+= antallBilletter;
        Billett[] billetter = new Billett[antallBilletter];
        for (int i = 0; i < antallBilletter; i++) {
            billetter[i] = new StaaplassBillett(getTribunenavn(), getKapasitet());
        }
        return billetter;
    }

    @Override
    public Billett[] kjøpBilletter(String[] kunder) {
        return kjøpBilletter(kunder.length);
    }
}
