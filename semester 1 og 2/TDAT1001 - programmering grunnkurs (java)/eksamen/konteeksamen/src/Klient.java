public class Klient {
    public static void main(String[] args) {
        Sykehus sykehus1 = new Sykehus();
        sykehus1.registrerNyAvdeling(new Avdeling());
        sykehus1.getAvdelinger().get(0).registrerNyPerson(new Person());
    }
}
