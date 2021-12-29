public class VIP extends Sitte {
    private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {

    }

    @Override
    public int finnInntekt() {
        return (getPris() * finnAntallSolgteBilletter());
    }

    @Override
    public Billett[] kjøpBilletter(int antallBilletter) {

    }
}