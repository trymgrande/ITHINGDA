public class VIP extends Tribune {
    private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int antallSolgteBilletter = 0;
        //itererer gjennom rader
        for (int i = 0; i < tilskuer.length; i++) {
            //itererer gjennom kolonner
            for (int j = 0; j < tilskuer[i].length; j++) {
                if (tilskuer[i][j] != null) {
                    antallSolgteBilletter++;
                }
            }
        }
        return antallSolgteBilletter;
    }

    @Override
    public int finnInntekt() {
        return getPris() * finnAntallSolgteBilletter();
    }
}
