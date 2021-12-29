import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KortstokkTest {

    private Kortstokk instance;
    private static final int forsteBokstav = 'A';
    private static final int sisteBokstav = 'Z';

    public KortstokkTest() {
    }


    @BeforeEach
    public void setUp() {
        //lag ny kortstokken før hver test
        instance = new Kortstokk();
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    public void testGetKortstokk() {  // test om kortstokken inneholder alle bokstavene i alfabetet, og ingen duplikater
        System.out.println("testGetKortstokk()");
        ArrayList<Character> result = instance.getKortstokk();
        ArrayList<Character> expResult = new ArrayList<Character>();
        for (int i = forsteBokstav; i <= sisteBokstav; i++) {
            expResult.add(((char) i));
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAntallBokstaver(){
        System.out.println("testGetAntallBokstaver()");
        int result = instance.getAntallBokstaver();
        int expResult = sisteBokstav - forsteBokstav + 1; // må plusse på 1 for å få med z
        assertEquals(expResult, result);
    }

    @Test
    public void testGetKort_normalsituasjon() throws Exception{ // tester at etter ett tall er trukket så er arraylisten med kort minket med 1
        System.out.println("testGetKort_normalsituasjon()");
        // trekker tre kort
        char bokstav = instance.getKort();
        bokstav = instance.getKort();
        bokstav = instance.getKort();
        int result = instance.getAntallBokstaver();
        int expResult = (sisteBokstav - forsteBokstav+1)- 3;  // +1 for å få med z, - 3 for tre trekte kort.
        assertEquals(expResult, result);
    }

    @Test // tester at det kastes ett unntak når kortstokken er tom
    public void testGetKort_tomkortstokk() throws Exception{
        System.out.println("testGetKort_tomkortstokk()");
        int antallBokstaver = instance.getAntallBokstaver();
        for (int i=forsteBokstav; i<=sisteBokstav;i++){
            char bokstav = instance.getKort();
        }
       // char bokstav = instance.getKort(); // skal kaste unntak, kortstokken er nå tom
        assertThrows(Exception.class,() -> instance.getKort());
    }
}