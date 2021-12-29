public class Talltabell {
    private int[] tabell;// = new int[1000000];
    public final int BOBLEGRENSE;

    public Talltabell() {
        this(0);
    }

    public Talltabell(int boblegrense) {
        this(boblegrense, randomTabell(1000000));
    }

    public Talltabell(int boblegrense, int[] tab) {
        BOBLEGRENSE = boblegrense;
        tabell = tab;
    }


    public void quicksort() {
        quicksort(0, tabell.length - 1);
    }

    private int splitt(int v, int h) {
        int pivot = v + (h - v) / 2;
        int pivotverdi = tabell[pivot];
        int mindre = v - 1;
        bytt(pivot, h);
        for (int i = v; i < h; i++) {
            if (tabell[i] < pivotverdi) {
                mindre++;
                bytt(mindre, i);
            }
        }
        bytt(mindre + 1, h);
        return mindre + 1;
    }

    private void quicksort(int v, int h) {

        if (h - v <= BOBLEGRENSE) {
            System.out.println(v + ", " + h);
            boblesorter(v, h);
            return;
        }
        System.out.println(v + ", " + h);
        int splitt = splitt(v, h);

        quicksort(v, splitt - 1);
        quicksort(splitt + 1, h);
    }

    private void bytt(int a, int b) {
        int hjelp = tabell[a];
        tabell[a] = tabell[b];
        tabell[b] = hjelp;
    }

    //bubblesort helping algorithm
    private void boblesorter(int v, int h) {
        for (int i = v; i <= h; i++) {
            for (int j = v; j < h - (i - v); j++) {
                if (tabell[j + 1] < tabell[j]) {
                    int hjelp = tabell[j + 1];
                    tabell[j + 1] = tabell[j];
                    tabell[j] = hjelp;
                }
            }
        }
    }

    public boolean rekkefølge() {
        for (int i = 0; i < tabell.length - 1; i++) {
            if (tabell[i] > tabell[i + 1]) return false;
        }
        return true;
    }

    public long sum() {
        long sum = 0;
        for (int i = 0; i < tabell.length; i++) {
            sum += tabell[i];
        }
        return sum;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i : tabell) {
            ret += i + "\n";
        }
        return ret;
    }

    public static int[] randomTabell(int størrelse) {
        int[] tabell = new int[størrelse];
        for (int i = 0; i < tabell.length; i++) {
            tabell[i] = (int) (Math.random() * 10000);
        }
        return tabell;
    }

    public static int[] tabellKopi(int[] tab) {
        int[] nyTab = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            nyTab[i] = tab[i];
        }
        return tab;
    }

    public static void kjørTest(Talltabell tab) {
        System.out.println("Tabell med boblegrense = " + tab.BOBLEGRENSE);
        long sum = tab.sum();
//        System.out.println("Sum før: " + tab.sum());
//        System.out.println("Riktig rekkefølge før: " + tab.rekkefølge());
        long tid = -System.currentTimeMillis();
        tab.quicksort();
        tid += System.currentTimeMillis();
        System.out.println("Sorteringen tok " + tid + " ms");
//        System.out.println("Sum etter: " + tab.sum());
//        System.out.println("Riktig rekkefølge etter: " + tab.rekkefølge() + "\n");
        if (sum == tab.sum() && tab.rekkefølge()) System.out.println("Begge testene riktig\n");
        else System.out.println("Minst èn test feilet\n");
    }

    public static void main(String[] args) {
        //gererate arrays
        int[] a = randomTabell(1000000);
        int[] b = tabellKopi(a);
        int[] c = tabellKopi(a);
        int[] d = tabellKopi(a);
        int[] e = tabellKopi(a);
        int[] f = tabellKopi(a);
        int[] g = tabellKopi(a);
        int[] h = tabellKopi(a);

        Talltabell ta = new Talltabell(0, a);
        Talltabell tb = new Talltabell(10, b);
        Talltabell tc = new Talltabell(50, c);
        Talltabell td = new Talltabell(100, d);
        Talltabell te = new Talltabell(250, e);
        Talltabell tf = new Talltabell(1000, f);
        Talltabell tg = new Talltabell(2500, g);
        Talltabell th = new Talltabell(5000, h);

        kjørTest(ta);
        kjørTest(tb);
        kjørTest(tc);
        kjørTest(td);
        kjørTest(te);
        kjørTest(tf);
        kjørTest(tg);
        kjørTest(th);
    }
}