import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Hashtabell2 {
    //2 ^ 7 = 128
//    static Node[] nameList = new Node[6250009];
    static int[] randomNumberList = new int[5000000];
    static int[] hashNumberList = new int[6000000];
//    static EnkelLenke nameList = new EnkelLenke(new Node(0, null));
    static HashMap<Integer, Integer> hashMap = new HashMap<>(6000000);

    static int crashesWhileInserting = 0;
    static int crashesWhileSearching = 0;

    public static boolean generateNumbers() {
        Random rand = new Random();
        for (int i = 0; i < randomNumberList.length; i++) {
            randomNumberList[i] = rand.nextInt(10000000);
        }
        return true;
    }

    public static boolean putNumbers() {
        for (int i = 0; i < randomNumberList.length; i++) {
            hashMap.put(randomNumberList[i], randomNumberList[i]);
        }
        return true;
    }

    public static boolean insertNumbers() {
        for (int i = 0; i < randomNumberList.length; i++) {
//            insertNumber(hashNumberList[randomNumberList[i]]);
            insertNumber(randomNumberList[i]);
        }
        return true;
    }

    public static boolean insertNumber(int number) {
        //bruker number som k
        int index = (h1(number) + h2(number)) % hashNumberList.length;
        for (int i = 0; i < randomNumberList.length; i++) {
            index += h2(number);
            index = index % hashNumberList.length;
            if (hashNumberList[index] == 0) {
                hashNumberList[index] = number;
            }


//            if (hashNumberList[hashFunction(number, i)] == 0) {
//                hashNumberList[hashFunction(number, i)] = number;
//                return true;
//            }
        }

        return false; //ingen ledig plass
    }

//    public static boolean insertName(String name, int i) {
////        int ledig = probeLedig(generateK(name));
//        if (nameList[i] == null) {
//            nameList[i] = new Node(name);
//            return true;
//        }
//        crashesWhileInserting++;
//        System.out.println("kræsj ved innsetting: " + name + " - " + nameList[i].element);
//        Node node = nameList[i];
//        while (true) {
//            if (node.neste == null) {
//                node.neste = new Node(name);
//                return true;
//            }
//            crashesWhileInserting++;
//            System.out.println("kræsj ved innsetting: " + name + " - " + node.neste.element);
//            node = node.neste;
//        }
//    }
//
//    //lookup
//    public static String getName(String name, int i) {
//        if (nameList[i] == null) {
//            return null;
//        }
//        if (nameList[i].element.equals(name)) {
//            return nameList[i].element;
//        }
//        crashesWhileSearching++;
//        System.out.println("kræsj ved lookup: " + name + " - " + nameList[i].element);
//        Node node = nameList[i];
//        while (node.neste != null) {
//            if (node.neste.element.equals(name)) {
//                return nameList[i].element;
//            }
//            crashesWhileSearching++;
//            System.out.println("kræsj ved lookup: " + name + " - " + node.neste.element);
//            node = node.neste;
//        }
//        return null;
//    }

    public static int generateK(String name) {
        int k = 0;
        for (int i = 0; i < name.length(); i++) {
            k += (name.charAt(i) * (i+1));
//            System.out.println(k);
        }
        return k;
    }


//    public static int hashFunction(int k, int j) {
////        return (h1(k) + j * h2(k)) % hashNumberList.length;
//        return (h1(k) + h2(k)) % hashNumberList.length;
//    }

    public static int h1(int k) {
        return (k % hashNumberList.length);
    }

    public static int h2(int k) {
        return ((k % (hashNumberList.length - 1)) + 1);
    }

    public static void main(String[] args) throws IOException {
        //egen hashtabell:

//        generateList();
        generateNumbers();
        //tid start
        long tid = System.currentTimeMillis();
        insertNumbers();
        //tid slutt
        tid = System.currentTimeMillis() - tid;
        System.out.println("tid med egen hashtabell: " + tid);

        //java sin hashtabell:

        //tid start
        tid = System.currentTimeMillis();
        putNumbers();
        tid = System.currentTimeMillis() - tid;
        System.out.println("tid med innebygd hashtabell: " + tid);
//
//        //count elements
//        int elements = 0;
//        for (int i = 0; i < nameList.length; i++) {
//            System.out.println(nameList[i]);
//            if (nameList[i] != null) {
//                elements++;
//            }
//        }
//        System.out.println();
//        System.out.println(elements);
//        double lastfaktor = (double) elements / nameList.length;
//        System.out.println("lastfaktor: " + lastfaktor);
//        System.out.println("kræsj ved innsetting: " + crashesWhileInserting + ", snitt pr pers: " + (double) crashesWhileInserting / elements);
//        System.out.println("kræsj ved søking: " + crashesWhileSearching + ", snitt pr pers: " + (double) crashesWhileSearching / 1);
    }
}