public class Klient {
    public static void main(String[] args) {
        Standard s1 = new Standard(new Kunde(1, "t", "e"));
        s1.getManedspris();

    }
}
