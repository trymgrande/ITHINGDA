public class Spiller {
    private String navn;
    private char bokstav;
    private int antallPoeng;

    public Spiller(String startNavn) {
        navn = startNavn;
        antallPoeng = 0;
    }

    public String getNavn() {
        return navn;
    }

    public char getBokstav() {
        return bokstav;
    }

    public int getAntallPoeng() {
        return antallPoeng;
    }

    public boolean setBokstav(char nyBokstav) {
        if (nyBokstav >= Kortstokk.forsteBokstav && nyBokstav <= Kortstokk.sisteBokstav) {
            bokstav = nyBokstav;
            return true;
        } else return false;
    }

    public void okAntallPoeng() {
        antallPoeng++;
    }


    public String toString() {
        return navn + " har bokstav: " + bokstav + " og antall Poeng: " + antallPoeng;
    }
}

