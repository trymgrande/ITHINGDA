/*
Eksamen_H2017
----------
String[] muligheter
int {LEGG_TIL_ORD}
int {LEGG_TIL_DEFINISJON}
int {AVSLUTT}
int valg


*/
import javax.swing.JOptionPane;

class Eksamen_H2017 {
    public static void main(String[] args) {
        String[] muligheter = {"Legg til ord", "Legg til definisjon", "Avslutt"};
        final int LEGG_TIL_ORD = 0;
        final int LEGG_TIL_DEFINISJON = 1;
        final int AVSLUTT = 2;
        int valg = JOptionPane.showOptionDialog(null, "Velg", "Eksamen des 2017", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
        String navn = "Ordboka";
        Ordbok ordbok = new Ordbok(navn, 10);
        String[] enDefinisjon = {"når noe er jiha"};
        Ord etOrd = new Ord("jiha", enDefinisjon );

        while (valg != AVSLUTT){
            switch (valg){
                case LEGG_TIL_ORD:
                    /* OPPGAVE 4a) skal inn her */

                    break;
                case LEGG_TIL_DEFINISJON:
                    /* OPPGAVE 4b) skal inn her */

                    break;
                default: break;
            }
        }
    }
}



















class Ord {
    String ord;
    String[] definisjoner;

    public Ord(String ord, String[] definisjoner) {
        this.ord = ord;
        this.definisjoner = definisjoner;
    }
    String getOrd() {
        return ord;
    }
    String[] getDefinisjoner() {
        return definisjoner;
    }
    public boolean equals(Object o) {
        //sjekk instance of
        if (!(o instanceof Ord)) {
            return false;
        }
        //sjekk om objektene er like
        if (this == o) {
            return true;
        }
        //caster og sjekker om attributten ord er like
        Ord ord2 = (Ord) o;
        return this.ord.equals(ord2.getOrd());
    }
    public String toString() {
        String tempString = "";
        tempString += ord+":\n";
        for (int i = 0; i < definisjoner.length-1; i++) {
            tempString += (i+1)+". "+definisjoner[i];
        }
        return tempString;
    }
    public void utvidDefinisjoner() {
        String[] tempDefinisjoner = new String[definisjoner.length+1];
        for (int i = 0; i < definisjoner.length; i++) {
            tempDefinisjoner[i] = definisjoner[i];
        }
        definisjoner = tempDefinisjoner;
    }
    public boolean sjekkGyldigDefinisjon(String nyDefinisjon) {
        for (int i = 0; i < definisjoner.length; i++) {//sjekker hver def
            if (nyDefinisjon == definisjoner[i]) {
                System.out.println("definisjon finnes allerede");
                return false;
            }
        }
        return true;
    }
    public boolean leggTilDefinisjon(String nyDefinisjon) {
        //sjekke om def er reg fra før
        if (sjekkGyldigDefinisjon(nyDefinisjon)) {
            //legg til def
            utvidDefinisjoner();
            definisjoner[definisjoner.length-1] = nyDefinisjon;
            return true;
        }
        else {
            return false;
        }
    }

}


























class Ordbok implements java.io.Serializable {
    private String ordbokNavn;
    private Ord[] ordbok;
    private int antallReg;
    private final int MAKS_ANTALL_ORD = 10;
    private String filnavn = "ordliste.ser";

    public Ordbok(String ordbokNavn,int MAKS_ANTALL_ORD) {
        this.ordbokNavn = ordbokNavn;

        try {
            //sjekke om data finnes på ordliste

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}