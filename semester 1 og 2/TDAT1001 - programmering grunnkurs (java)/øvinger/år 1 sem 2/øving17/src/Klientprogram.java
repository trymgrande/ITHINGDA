

public class Klientprogram  {
    public static void main(String[] args) {
        int verdi;
        Valuta[] valutaliste = {
                new Valuta("Euro", 8.10, 1), new Valuta("US Dollar", 6.23, 1),
                new Valuta("Britiske pund", 12.27, 1), new Valuta("Svenske kroner", 88.96, 100),
                new Valuta("Danske kroner", 108.75, 100), new Valuta("Yen", 5.14, 100),
                new Valuta("Islandske kroner", 9.16, 100), new Valuta("Norske kroner", 100, 100)
        };

        //while loop lytte etter valg i liste


        //test
        verdi = 29;
        int f = 1;
        int t = 3;

        Valuta fraValuta = valutaliste[f];//eur
        Valuta tilValuta = valutaliste[t];//nok

        System.out.println("konverterer " + verdi + " " + fraValuta.getNavn() + " til " + tilValuta.getNavn() + ":");
        System.out.println(fraValuta.konverter(verdi, tilValuta));

    }
}