import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class VektetGraf {
    int[][] graf;

    public VektetGraf() {
    }

    public VektetGraf(String path) {
        lesFraFil(path);
    }


    void nyKant(int fra, int til, int vekt) {
        graf[fra][til] = vekt;
    }

    boolean fjernKant(int fra, int til) {
        graf[fra][til] = 0;
        return false;
    }

    boolean lesFraFil(String path) {
        return lesFraFil(new File(path));
    }

    public boolean lesFraFil(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            boolean forste = true;
            while (br.ready()) {
                String[] split = br.readLine().trim().split("\\s+");
                int t1 = Integer.parseInt(split[0]);
                int t2 = Integer.parseInt(split[1]);

                if (forste) {
                    forste = false;
                    graf = new int[t1][t1];
                } else {
                    int t3 = Integer.parseInt(split[2]);
                    nyKant(t1, t2, t3);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public int[] breddeForstSokFlyt(int kilde, int sluk) {

        /*På formen:
          {
              {distanse, forgjenger}
          }*/
        int[] distanser = new int[graf.length];
        int[] forgjengere = new int[graf.length];
        for (int i = 0; i < distanser.length; i++) {
            distanser[i] = Integer.MAX_VALUE;
            forgjengere[i] = -1;
        }
        distanser[kilde] = 0;

        ArrayList<Integer> kø = new ArrayList<>();
        kø.add(kilde);
        while (kø.size() > 0) {
            int node = kø.remove(0);
            int[] naboer = graf[node];
            for (int i = 0; i < naboer.length; i++) {
                int kant = naboer[i];
                if (kant == 0) continue;

                if (forgjengere[i] == -1 && i != kilde) {
                    kø.add(i);
                }

                if (distanser[i] == Integer.MAX_VALUE) {
                    distanser[i] = distanser[node] + 1;
                    forgjengere[i] = node;
                }

                if (i == sluk) {
                    return forgjengere;
                }

            }
        }
        return null;
    }


    void edmondKarp(int kilde, int sluk) {
        int maksFlyt = 0;

        System.out.println();
        System.out.println("------------------------");
        System.out.println("Maks flyt fra " + kilde + " til " + sluk + " med Edmond-Karp");
        System.out.println("Økning : Flytøkende vei");

        while (true) {
            int[] forgjengere = breddeForstSokFlyt(kilde, sluk);
            if (forgjengere == null) break;

            int node = sluk;
            int flaskehals = Integer.MAX_VALUE / 2;
            ArrayList<Integer> vei = new ArrayList<>();
            ArrayList<int[]> kanter = new ArrayList<>();
            while (node != kilde) {
                vei.add(node);
                int[] kant = {forgjengere[node], node};
                int kapasitet = graf[kant[0]][kant[1]];
                kanter.add(kant);
                if (kapasitet < flaskehals) flaskehals = kapasitet;
                node = forgjengere[node];
            }
            vei.add(kilde);
            if (flaskehals <= 0) break;
            for (int[] i : kanter) {
                addKantFlyt(i[0], i[1], flaskehals);
            }

            String print = "     " + flaskehals + "   ";
            for (int i = vei.size() - 1; i >= 0; i--) {
                print += vei.get(i)+" ";
            }
            System.out.println(print);

            maksFlyt += flaskehals;
        }
        System.out.println("Maks flyt: " + maksFlyt);
    }

    void addKantFlyt(int fra, int til, int flyt) {
        graf[fra][til] -= flyt;
        graf[til][fra] += flyt;
    }


    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        VektetGraf flytgraf1 = new VektetGraf();
        flytgraf1.lesFraFil("src/flytgraf1");
        flytgraf1.edmondKarp(1, 7);

        VektetGraf flytgraf2 = new VektetGraf();
        flytgraf2.lesFraFil("src/flytgraf2");
        flytgraf2.edmondKarp(0, 1);

        VektetGraf flytgraf3 = new VektetGraf();
        flytgraf3.lesFraFil("src/flytgraf3");
        flytgraf3.edmondKarp(0, 1);

    }
}