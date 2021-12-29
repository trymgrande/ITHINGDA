import java.util.ArrayList;
import java.util.Random;

public class Kortstokk {
    private ArrayList<Character> kortstokk;
    public static final int forsteBokstav = 'A';
    public static final int sisteBokstav = 'Z';


    public Kortstokk(){
        kortstokk = new ArrayList<Character>();
        for (int i = forsteBokstav; i <= sisteBokstav; i++) {
            kortstokk.add(((char) i));
        }
    }

    public ArrayList<Character> getKortstokk() {
        return kortstokk;
    }

    public int getAntallBokstaver(){
        if (kortstokk != null) return kortstokk.size();
        else return -1;
    }

    public Character getKort(){
        Random gen = new Random(17);
        int indeks = gen.nextInt(getAntallBokstaver());
        Character trukketKort = kortstokk.get(indeks);
        kortstokk.remove(indeks);
        return trukketKort;
    }
}
