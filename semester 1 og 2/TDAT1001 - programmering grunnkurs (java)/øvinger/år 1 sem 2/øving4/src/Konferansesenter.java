import java.util.ArrayList;

public class Konferansesenter {
    private ArrayList<Rom> romliste = new ArrayList<Rom>();
    public Konferansesenter() {

    }
    //Reserver rom, gitt tidspunkt fra og til, antall personer samt navn og telefonnummer til kunden.
    //(Her skal romnummer ikke være parameter, metoden skal selv finne et rom som er ledig og med plass til
    // det oppgitte antallet personer.)
    public boolean registrerNyttRom(int romstørrelse, int romnummer) {
        //sjekke om romnummeret eksisterer
        for (int i = 0; i < romliste.size(); i++) {
            if (romliste.get(i).getRomnummer() == romnummer) {
                return false;
            }
        }
        romliste.add(new Rom(romstørrelse, romnummer));
        return true;
    }
    public boolean reserverRom(Tidspunkt fraTid, Tidspunkt tilTid, int antallPersoner, String kundenavn,
                               String kundeTlf) {
        Kunde nyKunde = new Kunde(kundenavn, kundeTlf);
        //finner ledig rom
        for (int i = 0; i < romliste.size(); i++) {
            //reserverer rommet
            if (romliste.get(i).reserverRom(fraTid, tilTid, nyKunde)) {
                return true;
            }
        }
        return false;
    }

    public int getAntallRom() {
        return romliste.size();
    }
    public Rom getBestemtRomGittIndeks(int i) {
        return romliste.get(i);
    }
    public Rom getBestemtRomGittRomnummer(int romnummer) {
        for (int i = 0; i < romliste.size(); i++) {
            if (romliste.get(i).getRomnummer() == romnummer) {
                return romliste.get(i);
            }
        }
        return null;
    }
}