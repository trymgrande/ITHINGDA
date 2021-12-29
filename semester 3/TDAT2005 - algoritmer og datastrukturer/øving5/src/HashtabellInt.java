import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HashtabellInt {
    public final int[] tabell;
    private int antallElementer = 0;
    private int kollisjoner = 0;

    public HashtabellInt(int lengde) {
        tabell = new int[lengde];
    }

    public void settInn(int tall) {
        int ind1 = lagIndex1(tall);
        if (tabell[ind1] == 0) {
            tabell[ind1] = tall;
        } else {
            kollisjoner++;
            //h(h1,h2,i,m)=(h1+i*h2)%m
            int indPluss = lagIndex2(tall);
            int ind2 = (ind1 + indPluss) % tabell.length;
            while (tabell[ind2] != 0) {
                kollisjoner++;
                ind2 = (ind2 + indPluss) % tabell.length;
            }
            tabell[ind2] = tall;
        }
        antallElementer++;
    }

    private int lagIndex1(int tall) {
        return tall % tabell.length;
    }

    private int lagIndex2(int tall) {
        return tall % (tabell.length - 1) + 1;
    }

    public void importerTabell(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            settInn(tab[i]);
        }
    }

    public int getKollisjoner() {
        return kollisjoner;
    }

//    public int nullIndekser() {
//        int teller = 0;
//        for (int i : tabell) {
//            if (i == 0) teller++;
//        }
//        return teller;
//    }

    public double lastfaktor() {
        return (double) antallElementer / tabell.length;
    }

    public int getAntallElementer() {
        return antallElementer;
    }


    public static void main(String[] args) {
        HashtabellInt ht = new HashtabellInt(6000011);//7167623
        int[] randomTab = randomTabell(5000000);

        long tid = -System.currentTimeMillis();
        ht.importerTabell(randomTab);
        tid += System.currentTimeMillis();
        System.out.println("Innsetting tok " + tid + " millisekund for egen klasse");

//        int kollisjoner = ht.getKollisjoner();
//        System.out.println("Antall kollisjoner: " + kollisjoner);

//        System.out.println(ht.nullIndekser());
//        System.out.println("Antall kollisjoner per person: " + kollisjoner / (double) ht.opptatteIndekser()); //ht.getAntallElementer());
//        System.out.println("Lastfaktor: " + ht.lastfaktor());

        HashMap<Integer,Integer> map = new HashMap<>();
//        HashMap<Integer,Integer> map = new HashMap<>(5000000);
        tid = -System.currentTimeMillis();
        for(int i = 0;i<randomTab.length;i++) {
            map.put(randomTab[i], randomTab[i]);
        }
        tid +=System.currentTimeMillis();
        System.out.println("Innsetting tok " + tid + " millisekund for java.util.HashMap");
    }

    public static int[] randomTabell(int størrelse) {
        int[] tab = new int[størrelse];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (int) (Math.random() * (Integer.MAX_VALUE / 1.2) + 1);
        }
        return tab;
    }
}
