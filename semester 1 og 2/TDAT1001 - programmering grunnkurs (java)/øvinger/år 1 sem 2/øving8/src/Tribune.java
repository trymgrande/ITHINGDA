import java.util.ArrayList;

abstract public class Tribune {
    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }
    public String getTribunenavn() {
        return tribunenavn;
    }
    public int getKapasitet() {
        return kapasitet;
    }
    public int getPris() {
        return pris;
    }
    abstract public int finnAntallSolgteBilletter();
    abstract public int finnInntekt();
    abstract public Billett[] kjøpBilletter(int antallBilletter);
    abstract public Billett[] kjøpBilletter(String[] billettKjøpere);
}