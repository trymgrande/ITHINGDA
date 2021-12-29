import java.util.ArrayList;

public class Avdeling {

    private ArrayList<Person> personer = new ArrayList<Person>();

    public Avdeling() {

    }
    public boolean registrerNyPerson(Person nyPerson) {
        personer.add(nyPerson);
        return true;
    }
}
