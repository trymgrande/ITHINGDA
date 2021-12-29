import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SpillerTest {
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test of getNavn method, of class Spiller.
     */
    @Test
    public void testGetNavn() {
        System.out.println("Spiller: getNavn");
        Spiller instance = new Spiller("Grethe");
        String expResult = "Grethe";
        String result = instance.getNavn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBokstav method, of class Spiller.
     */
    @Test
    public void testGetBokstav() {
        System.out.println("Spiller: getBokstav");
        Spiller instance =  new Spiller("Grethe");
        instance.setBokstav('A');
        char expResult = 'A';
        char result = instance.getBokstav();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAntallPoeng method, of class Spiller.
     */
    @Test
    public void testGetAntallPoeng() {
        System.out.println("Spiller: getAntallPoeng");
        Spiller instance =  new Spiller("Grethe");
        int expResult = 0;
        int result = instance.getAntallPoeng();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBokstav method, of class Spiller.
     */
    @Test
    public void testSetBokstav() {
        System.out.println("Spiller: setBokstav");
        char nyBokstav = 'B';
        Spiller instance = new Spiller("Grethe");
        instance.setBokstav(nyBokstav);
        char expResult = 'B';
        char result = instance.getBokstav();
        assertEquals(expResult, result);
    }

    /**
     * Test of okAntallPoeng method, of class Spiller.
     */
    @Test
    public void testOkAntallPoeng() {
        System.out.println("Spiller: okAntallPoeng");
        Spiller instance = new Spiller("Grethe");
        instance.okAntallPoeng();
        instance.okAntallPoeng();
        instance.okAntallPoeng();
        instance.okAntallPoeng();
        int expResult = 4;
        int result = instance.getAntallPoeng();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Spiller.
     */
    @Test
    public void testToString() {
        System.out.println("Spiller: toString");
        Spiller instance = new Spiller("Grethe");
        instance.setBokstav('A');
        String expResult = "Grethe har bokstav: A og antall Poeng: 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}