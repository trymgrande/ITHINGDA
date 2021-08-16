import java.util.ArrayList;

public class Sykehus {
    private String navn;
    private ArrayList<Avdeling> avdelinger = new ArrayList<Avdeling>();

    Sykehus(){

    }
    public boolean registrerNyAvdeling(Avdeling nyAvdelng) {
        avdelinger.add(nyAvdelng);
        return true;
    }
    public ArrayList<Avdeling> getAvdelinger() {
        return avdelinger;
    }


}