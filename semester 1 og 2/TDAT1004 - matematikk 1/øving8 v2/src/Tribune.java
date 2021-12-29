public abstract class Tribune {
    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }

    public abstract int finnAntallSolgteBilletter();

    public abstract int finnInntekt();

    public int getPris() {
        return pris;
    }

    public int getKapasitet() {
        return kapasitet;
    }

    public String getTribunenavn() {
        return tribunenavn;
    }

    //returnerer null for vip
    public abstract Billett[] kjøpBilletter(int antallBilletter);

    //returnerer samme som nr 1 utenom for vip
    public abstract Billett[] kjøpBilletter(String[] kunder);
}
