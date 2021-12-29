public class Sitte extends Tribune   {
    private int [] antOpptatt;  // tabellstørrelse: antall rader

    //På sittetribuner begrenses dette av at alle billetter som selges i en bestilling, skal være på samme rad
    public Sitte(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int antallOpptatt = 0;
        for (int i = 0; i < antOpptatt.length; i++) {
            antallOpptatt += antOpptatt[i];
        }
        return antallOpptatt;
    }

    @Override
    public int finnInntekt() {
        return (getPris() * finnAntallSolgteBilletter());
    }

    @Override
    public Billett[] kjøpBilletter(int antallBilletter) {
        Billett[] billetter = new Billett[antallBilletter];
        //finner ledig rad
        for (int i = 0; i < antOpptatt.length; i++) {
            if (antallBilletter <= antOpptatt[i]) {
                //raden er ledig
                for (int j = 0; j < antallBilletter; j++) {
                    billetter[j] = new SitteplassBillett(getTribunenavn(), getPris(), i, (j + antOpptatt[i]));
                    antOpptatt[i]++;
                }
                return billetter;
            }
        }
        return null;
    }

    @Override
    public Billett[] kjøpBilletter(String[] billettKjøpere) {
        return kjøpBilletter(billettKjøpere.length);
    }
}
