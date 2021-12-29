import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Medlemsarkiv {
    private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();

    public int finnPoeng(int medlemsnummer, String passord) {
        for (int i = 0; i < medlemmer.size(); i++) {
            //søk opp medlnr
            if (medlemmer.get(i).getMedlNr() == medlemsnummer) {
                //sjekk passord
                if (medlemmer.get(i).okPassord(passord)) {
                    //passord korrekt
                    return medlemmer.get(i).getPoeng();
                }
            }
        }
        return -1;
    }
    public boolean registrerPoeng(int medlemsnummer, int antallPoeng) {
        for (int i = 0; i < medlemmer.size(); i++) {
            if (medlemmer.get(i).getMedlNr() == medlemsnummer) {
                medlemmer.get(i).registrerPoeng(antallPoeng);
            }
        }
        return false;
    }
    public int nyMedlem(Personalia pers, LocalDate innmeldt) {
        int medlemsnummer = finnLedigNr();
        medlemmer.add(new BasicMedlem(medlemsnummer, pers, innmeldt));
        return medlemsnummer;

    }
    private int finnLedigNr() {
        ArrayList<Integer> brukteMedlemsnummer = new ArrayList<Integer>();
        for (int i = 0; i < medlemmer.size(); i++) {
            brukteMedlemsnummer.add(medlemmer.get(i).getMedlNr());
        }
        int nyttMedlemsnumer = new Random().nextInt(1000000);
        for (int i = 0; i < brukteMedlemsnummer.size(); i++) {
            if (brukteMedlemsnummer.get(i) == nyttMedlemsnumer) {
                return finnLedigNr();
            }
        }
        return nyttMedlemsnumer;
    }
    public void sjekkMedlemmer(LocalDate testdato) {
        for (int i = 0; i < medlemmer.size(); i++) {
            if (medlemmer.get(i) instanceof BasicMedlem) {
                if (medlemmer.get(i).finnKvalPoeng(testdato) >= 75000) {
                    //oppgrader til gull
                    System.out.println(medlemmer.get(i) + " oppgraderes til gull");
                    medlemmer.add(i, new GullMedlem(medlemmer.get(i).getMedlNr(), medlemmer.get(i).getPersonalia(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
                }
                else if (medlemmer.get(i).finnKvalPoeng(testdato) >= 25000) {
                    //oppgrader til sølv
                    System.out.println(medlemmer.get(i) + "oppgraderes til sølv");
                    medlemmer.add(i, new SoelvMedlem(medlemmer.get(i).getMedlNr(), medlemmer.get(i).getPersonalia(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
                }
            }
            else if (medlemmer.get(i) instanceof SoelvMedlem) {
                if (medlemmer.get(i).finnKvalPoeng(testdato) >= 75000) {
                    //oppgrader til gull
                    System.out.println(medlemmer.get(i) + "oppgraderes til gull");
                    medlemmer.add(i, new GullMedlem(medlemmer.get(i).getMedlNr(), medlemmer.get(i).getPersonalia(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
                }
            }
        }
    }
}