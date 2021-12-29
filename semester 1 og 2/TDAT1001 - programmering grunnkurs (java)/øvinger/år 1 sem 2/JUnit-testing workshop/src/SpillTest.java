import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;   // lagt til som library til prosjektet  (fra maven)
import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpillTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test of spillEnOmgang method, of class Spill.
     */
    @Test
    public void testSpillEnOmgang() throws Exception {
        System.out.println("Spill: spillEnOmgang");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        String result = instance.spillEnOmgang();
        String expResult = "";

        boolean spiller1VilBytte = spiller1.getBokstav() > 'M';
        boolean spiller2VilBytte = spiller2.getBokstav() > 'M';

        boolean byttet =  spiller1VilBytte && spiller2VilBytte;
        String byttetLest = "De byttet ikke. ";
        if (byttet){
            byttetLest = "De byttet. ";
            expResult += spiller1.getNavn() + " trakk " + spiller2.getBokstav() + ". ";
            expResult += spiller2.getNavn() + " trakk " + spiller1.getBokstav() + ". ";
        }
        else{
            expResult += spiller1.getNavn() + " trakk " + spiller1.getBokstav() + ". ";
            expResult += spiller2.getNavn() + " trakk " + spiller2.getBokstav() + ". ";
        }
        expResult += byttetLest;
        expResult += spiller1 + " og " + spiller2;

        assertEquals(expResult, result);
    }

    /**
     * Test of bytteKort method, of class Spill.
     */
    @Test
    public void testBytteKort() throws Exception {
        System.out.println("Spill: bytteKort");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        instance.spillEnOmgang();

        char spiller1Bokstav = spiller1.getBokstav();
        char spiller2Bokstav = spiller2.getBokstav();
        instance.bytteKort();
        boolean expResult = true;
        boolean result = spiller1.getBokstav()==spiller2Bokstav && spiller2.getBokstav() == spiller1Bokstav;

        assertEquals(expResult, result);

    }

    /**
     * Test of beregnPoengForEnOmgang method, of class Spill.
     */
    @Test
    public void testBeregnPoeng() throws Exception {
        System.out.println("Spill: beregnPoengForEnOmgang");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        instance.spillEnOmgang();
        int spiller1Poeng = 0;
        int spiller2Poeng = 0;
        if (spiller1.getBokstav() < spiller2.getBokstav()) spiller1Poeng++;
        else if (spiller1.getBokstav() > spiller2.getBokstav()) spiller2Poeng++;
        boolean expResult = true;
        boolean result = spiller1.getAntallPoeng()==spiller1Poeng && spiller2.getAntallPoeng()==spiller2Poeng;
        assertEquals(expResult, result);
    }

    /**
     * Test of spillMangeOmganger method, of class Spill.
     */
    @Test
    public void testSpillMangeOmganger() throws Exception {
        System.out.println("Spill: spillMangeOmganger");
        int antallOmganger = 1;
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        String expResult = "\n";  // mellom hver omgang skal det skrives et linjeskift..
        String result = instance.spillMangeOmganger(antallOmganger);
        assertThat(result, containsString(expResult));
    }

    /**
     * Test of spillMangeOmganger method, of class Spill.
     * Exception forventes å kastes pga av en omgang for mye
     */
    @Test
    public void testSpillMangeOmganger_exeption() throws Exception {
        System.out.println("Spill: spillMangeOmganger");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        int antallOmganger = 14; // Blir en omgang for mye -
        assertThrows(Exception.class, ()-> instance.spillMangeOmganger(antallOmganger));
    }
    /**
     * Test of avsluttSpill method, of class Spill.
     */
    @Test
    public void testAvsluttSpill() throws Exception{
        System.out.println("Spill: avsluttSpill");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        instance.spillEnOmgang();
        String expResult= "Det ble uavgjort";
        if (spiller1.getAntallPoeng()>spiller2.getAntallPoeng()) expResult = spiller1.getNavn() + " vant:)";
        else if (spiller2.getAntallPoeng()>spiller1.getAntallPoeng()) expResult = spiller2.getNavn() + " vant:)";

        String result = instance.avsluttSpill();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Spill.
     */
    @Test
    public void testToString() throws Exception{
        System.out.println("Spill: toString");
        Spiller spiller1 = new Spiller("Grethe");
        Spiller spiller2 = new Spiller("Nils");
        Spill instance = new Spill(spiller1, spiller2);
        instance.spillEnOmgang();

        String expResult = spiller1 + "\n" + spiller2;
        String result = instance.toString();
        assertEquals(expResult, result);

    }
}