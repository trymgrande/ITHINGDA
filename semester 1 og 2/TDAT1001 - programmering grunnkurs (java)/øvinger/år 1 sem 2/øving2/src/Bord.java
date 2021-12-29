public class Bord {
    private final int antallBord;
    private String[] bordliste;

    public Bord(int antallBord) {
        this.antallBord = antallBord;
        bordliste = new String[antallBord];
    }
    public int getAntallLedigeBord() {
        int antallLedigeBord = 0;
        for (int i = 0; i < bordliste.length; i++) {
            if (bordliste[i] == null) {    //nullpointerexception
                antallLedigeBord++;
            }
        }
        return antallLedigeBord;
    }

    public int getAntallBord() {
        return antallBord;
    }

    public String[] getBordliste() {
        return bordliste;
    }

    public int reserverBord(String navn) {
        for (int i = 0; i < antallBord; i++) {
            if (bordliste[i] == null) {
                bordliste[i] = navn;
                return 0;
            }
        }
        return 1;
    }
    public int[] getBordReservertAv(String navn) {
        int[] bordReservertAvNavn = new int[antallBord];
        int antallBordReservertAvNavn = 0;
        for (int i = 0; i < antallBord; i++) {
            if (navn.equals(bordliste[i])) {    //nullpointerexception/tom
                bordReservertAvNavn[antallBordReservertAvNavn] = i;
                antallBordReservertAvNavn++;
            }
        }
        int[] bordReservertAvNavnKopi = new int[antallBordReservertAvNavn];
        for (int i = 0; i < bordReservertAvNavnKopi.length; i++) {
            bordReservertAvNavnKopi[i] = bordReservertAvNavn[i];
        }
        bordReservertAvNavn = bordReservertAvNavnKopi;
        return bordReservertAvNavn;
    }
    public int frigiBord(int[] bordSomSkalFrigjøres) {
        for (int i = 0; i < bordSomSkalFrigjøres.length; i++) {
            bordliste[bordSomSkalFrigjøres[i]] = null;
        }
        return 0;
    }
}