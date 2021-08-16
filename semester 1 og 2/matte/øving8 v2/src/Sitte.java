public class Sitte extends Tribune {
    private int [] antOpptatt; // tabellstørrelse: antall rader
    private int ANTALL_SETER_PER_RAD = getKapasitet() / antOpptatt.length;

    public Sitte(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int antallSolgteBilletter = 0;
        for (int i = 0; i < antOpptatt.length; i++) {
            antallSolgteBilletter += antOpptatt{i};
        }
        return antallSolgteBilletter;
    }

    @Override
    public int finnInntekt() {
        return getPris() * finnAntallSolgteBilletter();
    }

    @Override
    public Billett[] kjøpBilletter(int antallBilletter) {
        for (int i = 0; i < antOpptatt.length; i++) {
            if ((ANTALL_SETER_PER_RAD - antOpptatt{i]) >= antallBilletter) {
                antOpptatt[i] += antallBilletter;
                Billett[] billetter = new Billett[antallBilletter];
                for (int j = 0; j < antallBilletter; j++) {
                    billetter[j] = new SitteplassBillett(getPris(), );
            }
        }
    }

    @Override
    public Billett[] kjøpBilletter(String[] kunder) {
        return new Billett[0];
    }
}
