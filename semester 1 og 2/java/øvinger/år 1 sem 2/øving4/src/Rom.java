import java.util.ArrayList;

public class Rom {
    private int romnummer;
    private int romstørrelse;
    private ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();

    Rom(int romstørrelse, int romnummer) {
        this.romstørrelse = romstørrelse;
        this.romnummer = romnummer;
    }
    public boolean reserverRom(Tidspunkt fraTid, Tidspunkt tilTid, Kunde nyKunde) {

        //sjekke overlapp
        //for
        for (int i = 0; i < reservasjoner.size(); i++){

            if (reservasjoner.get(i).overlapp(fraTid, tilTid)) {
               return false;

            }
        }
        //reservere
        reservasjoner.add(new Reservasjon(fraTid, tilTid, nyKunde));
        return true;
    }
    public int getRomnummer() {
        return romnummer;
    }
    public int getRomstørrelse() {
        return romstørrelse;
    }
    public String toString() {
        String str = "";
        str += "romnummer: " + romnummer + ", romstørreslse: " + romstørrelse + "\n";
        //finner dette rommet sin reservasjon for å kalle toString til reservasjonen til rommet
        for (int i = 0; i < reservasjoner.size(); i++) {
            str += reservasjoner.get(i).toString();
        }
        return str;
    }

    public static void main(String[] args) {
        //tester klassen Rom
            //tester overlapp
        System.out.println("Totalt antall tester: 1");
        Rom r1 = new Rom(10, 1);
        Rom r2 = new Rom(10, 2);
        Rom r3 = new Rom(10, 3);
        if (r1.reserverRom(new Tidspunkt(199901221000L), new Tidspunkt(199901221030L), new Kunde("t", "0"))
                && r1.reserverRom(new Tidspunkt(199901221100L), new Tidspunkt(199901221130L), new Kunde("t", "0"))) {
            System.out.println("Tidspunkt: Test 1 vellykket.");
        }
    }
}