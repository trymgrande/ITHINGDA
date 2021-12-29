import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Hashtabell {
    //2 ^ 7 = 128
    static Node[] nameList = new Node[128];
//    static EnkelLenke nameList = new EnkelLenke(new Node(0, null));

    static int crashesWhileInserting = 0;
    static int crashesWhileSearching = 0;
    static int antallPersoner = 0;

    public static boolean insertNames() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/navn.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String name;
        while ((name = br.readLine()) != null) {
//            System.out.println(name);
            insertName(name, hashFunction(generateK(name)));
            antallPersoner++;
        }
        return true;
    }

    public static boolean insertName(String name, int i) {
//        int ledig = probeLedig(generateK(name));
        if (nameList[i] == null) {
            nameList[i] = new Node(name);
            return true;
        }
        crashesWhileInserting++;
        System.out.println("kræsj ved innsetting: " + name + " - " + nameList[i].element);
        Node node = nameList[i];
        while (true) {
            if (node.neste == null) {
                node.neste = new Node(name);
                return true;
            }
            crashesWhileInserting++;
            System.out.println("kræsj ved innsetting: " + name + " - " + node.neste.element);
            node = node.neste;
        }
    }


    //stackoverflow if not found
//    public static int probeName(int k, String name) {
//        if (nameList[hashFunction(k)] == (name)) {
//            return hashFunction(k);
//        }
//        crashesWhileSearching++;
//        return probeName(k+1, name);
//    }

    //lookup
    public static String getName(String name, int i) {
        if (nameList[i] == null) {
            return null;
        }
        if (nameList[i].element.equals(name)) {
            return nameList[i].element;
        }
        crashesWhileSearching++;
        System.out.println("kræsj ved lookup: " + name + " - " + nameList[i].element);
        Node node = nameList[i];
        while (node.neste != null) {
            if (node.neste.element.equals(name)) {
                return nameList[i].element;
            }
            crashesWhileSearching++;
            System.out.println("kræsj ved lookup: " + name + " - " + node.neste.element);
            node = node.neste;
        }
        return null;
    }

    public static int generateK(String name) {
        int k = 0;
        for (int i = 0; i < name.length(); i++) {
            k += (name.charAt(i) * (i+1));
//            System.out.println(k);
        }
        return k;
    }

    //søke gjennom navn public static

    public static int hashFunction(int k) {
        return (k % nameList.length);
    }

    public static void main(String[] args) throws IOException {
//        generateList();
        insertNames();
        //count elements
//        int elements = 0;
//        for (int i = 0; i < nameList.length; i++) {
////            System.out.println(nameList[i]);
//            if (nameList[i] != null) {
//                elements++;
//            }
//        }

        System.out.println(getName("Grande,Trym", hashFunction(generateK("Grande,Trym"))));
        System.out.println();
//        System.out.println(elements);
        double lastfaktor = (double) antallPersoner / nameList.length;
        System.out.println("lastfaktor: " + lastfaktor);
        System.out.println("kræsj ved innsetting: " + crashesWhileInserting + ", snitt pr pers: " + (double) crashesWhileInserting / antallPersoner);
        System.out.println("kræsj ved søking: " + crashesWhileSearching + ", snitt pr pers: " + (double) crashesWhileSearching / 1);
    }
}