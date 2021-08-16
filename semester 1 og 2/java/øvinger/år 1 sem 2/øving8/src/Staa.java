public class Staa extends Tribune{
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
        return (antSolgteBilletter * getPris());
    }
    //På ståtribuner er det bare den totale kapasiteten, og hvor mange som hittil er solgt, som setter begrensninger.


    @Override
    public Billett[] kjøpBilletter(int antallBilletter) {
        //sjekker om det er plass
        if ((antSolgteBilletter + antallBilletter) < getKapasitet()) {
            Billett[] billetter = new Billett[antallBilletter];
            for (int i = 0; i < antallBilletter;i++) {
                billetter[i] = new StaaplassBillett(getTribunenavn(), getPris());
                antSolgteBilletter++;
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
