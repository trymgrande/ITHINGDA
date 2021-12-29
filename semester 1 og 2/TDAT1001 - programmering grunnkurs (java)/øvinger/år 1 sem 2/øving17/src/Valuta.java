public class Valuta {
    private String navn;
    private double kurs;
    private int enhet;

    Valuta(String navn, double kurs, int enhet) {
        this.navn = navn;
        this.kurs = kurs;
        this.enhet = enhet;
    }

    public String getNavn() {
        return navn;
    }

    public double konverter(int verdi, Valuta tilValuta) {
        System.out.println(this.getNavn() + tilValuta.getNavn());
        Valuta fraValuta = this;
        double fraKurs = fraValuta.kurs / fraValuta.enhet;
        double tilKurs = tilValuta.kurs / tilValuta.enhet;
        double res = (verdi * fraKurs) / tilKurs;
        return res;
    }
}
