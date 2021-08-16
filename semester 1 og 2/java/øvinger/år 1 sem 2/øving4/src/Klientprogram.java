import javax.swing.*;
import java.awt.*;

public class Klientprogram {
    public static void main(String[] args) {

        //Begynn med å registrere alle rommene.
        //Les inn noen reservasjoner.
        //Skriv ut all registrert informasjon. Bruk metodene som finner antall rom og rom gitt indeks.
        //Prøv ut metoden som finnet er rom, gitt romnummer. La brukeren skrive inn romnummeret.
        // All info om rommet, inkludert reservasjonene skal skrives ut.

        Konferansesenter konferansesenter1 = new Konferansesenter();
        String[] options = {"registrer nytt rom", "reserver rom", "skriv ut antall rom", "finn rom med indeks", "finn rom med romnummer", "toString", "avslutt"};
        int valg = 7;
        while (valg != 6) {
            valg = JOptionPane.showOptionDialog(null, "velg et alternativ",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (valg) {
                case 0:  //registrer nytt rom
                    int romstørrelse = Integer.parseInt(JOptionPane.showInputDialog("romstørrelse: "));
                    int romnummer = Integer.parseInt(JOptionPane.showInputDialog("romnummer: "));
                    System.out.println(konferansesenter1.registrerNyttRom(romstørrelse, romnummer));
                    break;
                case 1:  //reserver rom
                    long fraTidLong = Long.parseLong(JOptionPane.showInputDialog("reservasjon fratid: (ååååmmddttmm)"));
                    long tilTidLong = Long.parseLong(JOptionPane.showInputDialog("reservasjon tilTid: (ååååmmddttmm)"));
                    int antallPersonerInt = Integer.parseInt(JOptionPane.showInputDialog("reservasjon antall personer: "));
                    String kundenavn = JOptionPane.showInputDialog("reservasjon kundenavn: ");
                    String kundeTlf = JOptionPane.showInputDialog("reservasjon kunde-tlf: ");
                    System.out.println(konferansesenter1.reserverRom(new Tidspunkt(fraTidLong), new Tidspunkt(tilTidLong), antallPersonerInt, kundenavn, kundeTlf));
                    //test
                    //System.out.println(konferansesenter1.reserverRom(new Tidspunkt(201901221000L), new Tidspunkt(201901221030L), 10, "trym", "00000000"));
                    break;
                case 2:  //skriv ut antall rom
                    System.out.println(konferansesenter1.getAntallRom());
                    break;
                case 3:  //finn rom med indeks
                    int i = Integer.parseInt(JOptionPane.showInputDialog("rom-indeks: "));
                    System.out.println(konferansesenter1.getBestemtRomGittIndeks(i).toString());
                    break;
                case 4:  //finn rom med romnummer
                    romnummer = Integer.parseInt(JOptionPane.showInputDialog("romnummer: "));
                    System.out.println(konferansesenter1.getBestemtRomGittRomnummer(romnummer).toString());
                    break;
                case 5:  //toString
                    //finner antall rom
                    System.out.println("antall rom: " + konferansesenter1.getAntallRom());
                    //looper gjennom rom og kaller tilsvarende toString
                    System.out.println("informasjon om hvert rom:");
                    for (int j = 0; j < konferansesenter1.getAntallRom(); j++) {
                        System.out.println(konferansesenter1.getBestemtRomGittIndeks(j).toString());
                    }
                    break;
                case 6:  //avslutt
                    break;
            }
        }
    }
}