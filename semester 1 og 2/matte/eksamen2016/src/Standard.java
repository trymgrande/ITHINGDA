public class Standard extends Abonnement {
    protected int manedspris = 99;
    protected int antallMmsGratis = 100;
    protected int prisPrMmsUtoverGrensen = 2;
    protected int antallGbGratis = 0;
    protected int prisPrGbUtoverGrensen = 79;
    Standard(Kunde nyKunde) {
        super(nyKunde);
    }
    public int getManedspris() {
        return manedspris;
    }
}
