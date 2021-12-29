public class Spill {
    private Spiller spiller1;
    private Spiller spiller2;
    private Kortstokk kortstokken;

    public Spill(Spiller spiller1, Spiller spiller2) {
        this.spiller1 = spiller1;
        this.spiller2 = spiller2;
        kortstokken = new Kortstokk();
    }

    public String spillEnOmgang() throws Exception {
        String resultat = "";
        // Trekk et kort for hver av spillerne fra kortstokken
        spiller1.setBokstav(kortstokken.getKort());
        spiller2.setBokstav(kortstokken.getKort());

        resultat += spiller1.getNavn() + " trakk " + spiller1.getBokstav() + ". ";
        resultat += spiller2.getNavn() + " trakk " + spiller2.getBokstav() + ". ";

        if (vilBeggeBytte(spiller1.getBokstav(), spiller2.getBokstav())) {
            bytteKort();
            resultat += "De byttet. ";
        } else resultat += "De byttet ikke. ";

        beregnPoengForEnOmgang();

        // resultat += spiller1.getNavn() + " har " + spiller1.getAntallPoeng() + " p. og "
        //   + spiller2.getNavn() + " har " + spiller2.getAntallPoeng() + " p.";

        resultat += spiller1 + " og " + spiller2;
        return resultat;
    }

    private boolean vilBeggeBytte(char a, char b) {
        return a > 'M' && b > 'M';
    }

    // bytt kort med hverandre dersom begge har høyere en M.
    public boolean bytteKort() {
        char hjelp = spiller1.getBokstav();
        spiller1.setBokstav(spiller2.getBokstav());
        spiller2.setBokstav(hjelp);
        return true;
    }

    public void beregnPoengForEnOmgang() {
        if (spiller1.getBokstav() < spiller2.getBokstav()) spiller1.okAntallPoeng();
        else if (spiller1.getBokstav() > spiller2.getBokstav()) spiller2.okAntallPoeng();
    }

    public String spillMangeOmganger(int antallOmganger) throws Exception {
        if (antallOmganger * 2 > kortstokken.getAntallBokstaver())
            throw new Exception("Det er ikke nok kort til så mange omganger");
        String resultat = "";
        for (int i = 0; i < antallOmganger; i++) {
            resultat += '\n' + spillEnOmgang();
        }
        return resultat;
    }

    public String avsluttSpill() {
        if (spiller1.getAntallPoeng() > spiller2.getAntallPoeng()) return spiller1.getNavn() + " vant:)";
        else if (spiller2.getAntallPoeng() > spiller1.getAntallPoeng()) return spiller2.getNavn() + " vant:)";
        else return "Det ble uavhgjort";
    }

    public String toString() {
        return spiller1.toString() + '\n' + spiller2.toString();
    }
}
