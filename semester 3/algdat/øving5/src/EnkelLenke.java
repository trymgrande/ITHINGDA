public class EnkelLenke {
    private Node hode = null;
    private static int antElementer;

    public EnkelLenke(Node hode) {
        antElementer = 0;
        this.hode = hode;
    }

    public static EnkelLenke genererEnkelLenke(int n) {
        Node hode = new Node(null);
        EnkelLenke el = new EnkelLenke(hode);
        antElementer++;
        for (int i = 1; i < n; i++) {
            Node nyNode = new Node(null);
            el.settInnBakerst(nyNode);
        }
        return el;
    }

    public static void finnLøsning(EnkelLenke el, int intervall) {
        Node n = el.finnHode();
        Node p = el.finnHode();

        while(n.finnNeste() != n){
            int num = 1;

            while(num != intervall){
                p = n;
                n = n.finnNeste();
                num++;
            }
            System.out.println("Dreper "+n.finnElement());
            p.setNeste(n.finnNeste());
            n = p.finnNeste();
        }
        System.out.println(n.finnElement() +" overlevde");
    }

    public int finnAntall() {
        return antElementer;
    }

    public Node finnHode() {
        return hode;
    }

//    public void settInnFremst(int verdi) {
//        hode = new Node(verdi, hode);
//        ++antElementer;
//    }

    public void settInnBakerst(Node nyNode) {
        if (hode != null) {
            Node denne = hode;
            while (denne.neste != null) {
                denne = denne.neste;
            }
            denne.neste = nyNode;
        } else {
            hode = nyNode;
        }
        ++antElementer;
    }

    public Node fjern(Node n) {
        Node forrige = null;
        Node denne = hode;
        while (denne != null && denne != n) {
            forrige = denne;
            denne = denne.neste;
        }
        if (denne != null) {
            if (forrige != null) {
                forrige.neste = denne.neste;
            } else {
                hode = denne.neste;
            }
            denne.neste = null;
            --antElementer;
            return denne;
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
//        EnkelLenke el1 = new EnkelLenke();
        EnkelLenke el1 = genererEnkelLenke(10);
        System.out.println(el1.finnAntall());
        finnLøsning(el1, 4);
    }
}