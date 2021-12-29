public class Algoritmer {
    Algoritmer sorterObjekt = new Algoritmer();

    public void sorter(int[] intListe) {
        int listeSlutt = intListe.length;

        java.util.Arrays.sort(intListe);

        for (int i = 0; i < listeSlutt; i++) {
            System.out.println(intListe[i]);
        }
    }
}