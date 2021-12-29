import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Testklient {
    public static void main(String[] args) {
        /*
        Restaurant enRestaurant = new Restaurant("Grande Grill", 1999, 4);
        System.out.println(enRestaurant.getAlder());
        System.out.println(enRestaurant.getAntallLedigeBord());
        System.out.println(enRestaurant.getAntallOpptattBord());
        System.out.println("reserverer: ");
        System.out.println(enRestaurant.reserverBord("trym", 3));
        System.out.println("reservert: ");
        int[] reservert = enRestaurant.getBordReservertAv("trym");
        for (int i = 0; i < reservert.length; i++) {
            System.out.println(reservert[i]);
        }
        System.out.println("frigir bord:");
        System.out.println(enRestaurant.frigiBord(new int[]{0, 1}));
        System.out.println("ledige");
        System.out.println(enRestaurant.getAntallLedigeBord());
        System.out.println("opptatte");
        System.out.println(enRestaurant.getAntallOpptattBord());
        */

        /*
        Brukeren skal kunne velge mellom følgende alternativer:

        reserver bord (les inn navn og antall bord)
        finn hvilke bord en bestemt person har reservert (les inn navn) (sikring som ikke overrider opptatt bord
        frigi bord (les inn aktuelle bordnummer)
        avslutt
        Sørg også for at metodene som finner alderen og endrer restaurantnavnet blir prøvd ut.
         */
        //spør brukeren om restaurantnavn, etableringsår, og antall bord.
        //opprett et Restaurant-objekt
        Restaurant enRestaurant = new Restaurant("Grande Grill", 1999, 4);
        int valg;
        do {
            //vis liste over mulige valg
            String[] options = {"reserver bord", "finn hvilke bord navn har reservert", "frigi bord i", "avslutt"};
            valg = JOptionPane.showOptionDialog(null,
                    "Returns the position of your choice on the array", "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            String navn;
            switch (valg) {
                case 0: // reservere et antall bord på et bestemt navn
                        //les inn navn og antall bord, og kall metode
                        navn = JOptionPane.showInputDialog("navn som skal reserveres:");
                        int antallBord = Integer.parseInt(JOptionPane.showInputDialog("antall bord som skal reserveres:"));
                        enRestaurant.reserverBord(navn, antallBord);
                        break;
                case 1: // finne alle bordene som er reservert på et bestemt navn
                        //les inn navn, og kall metode
                        navn = JOptionPane.showInputDialog("navn for å sjekke reserverte bord:");
                    int[] reservert = enRestaurant.getBordReservertAv(navn);
                    for (int i = 0; i < reservert.length; i++) {
                        System.out.println(reservert[i]);
                    }
                        break;
                case 2: // frigi en rekke bord, bordnummer er gitt
                        //les inn aktuelle bordnummer og kall metode...
                        //System.out.println("frigi bordnr, trykk enter mellom hvert nr: ");
                        int[] bordnummer = {0, 1, 2, 3};
                        enRestaurant.getBord().frigiBord(bordnummer);
                        System.out.println("ledige bord: " + enRestaurant.getBord().getAntallLedigeBord());
                        /*
                        Scanner scanner = new Scanner(System.in);
                        String input = scanner.nextLine();
                        int[] bordnummer = new int[enRestaurant.getBord().getAntallBord()];
                        for (int i = 0; input != ""; i++) {
                            int etBordnummer = Integer.parseInt(scanner.next());
                            bordnummer[i] = etBordnummer;
                            input = scanner.nextLine();
                        }
                        //fjerner tomme elementer
                        ArrayList<int[]> bordnummerAL = new ArrayList<int[]>(Arrays.asList(bordnummer));
                        bordnummerAL.removeAll(Arrays.asList(null));
                        */
                        break;
                case 3:
                    //avslutt
                    break;
            }
        }
        while (valg != 3);
    }
}