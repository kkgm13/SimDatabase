import model.Sim;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SimTest {

    private Sim sim1, sim2, sim3, sim4;

    @BeforeEach
    public void setUp() {
        sim1 = new Sim("Test 1", "441234567890", 7270, "United Kingdom", "O2",
                "Pay As You Go", 100.00, false, false, "To be destroyed", LocalDateTime.now());
        sim2 = new Sim("Test 2", "631234567890", 1234, "Philippines", "Smart",
                "Contract", 50.00, true, false, "Cross Check Account", LocalDateTime.now());
        sim3 = new Sim("Test 3", "601234567890", 4675, "Malaysia", "MyMaxis",
                "Monthly", 0.00, true, false, "LOST", LocalDateTime.now());
        sim4 = new Sim("Test 4", "101234567890", 8768, "United States America", "AT&T",
                "Monthly", 0.00, true, true, "Personal", LocalDateTime.now());
        //  String simName, String simNumber, int simPIN, String simCountry, String simProvider,
        //  String simType, double simCredit, boolean isRoaming, boolean isInUse, String simNotes
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test of toString method, of class SIM.
     */
    @Test
    public void testToString1() {
//        System.out.println("toString: ");
        String expResult = "Test 1\tUnited Kingdom\t(100.0)";
        String result = sim1.toString();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SIM.
     */
    @Test
    public void testToString2() {
//        System.out.println("toString: ");
        String expResult = "Test 2\tPhilippines\tRoaming\t(50.0)";
        String result = sim2.toString();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SIM.
     */
    @Test
    public void testToString3() {
//        System.out.println("toString: ");
        String expResult = "Test 3\tMalaysia\tRoaming\t(0.0: TopUp Required!)";
        String result = sim3.toString();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SIM.
     */
    @Test
    public void testToString4() {
//        System.out.println("toString: ");
        String expResult = "Test 4\tUnited States America\tRoaming\t(0.0: TopUp Required!)";
        String result = sim4.toString();
        assertEquals(expResult, result);
        fail("Change REQUIRED to compensate In Use parameter");
    }

    /**
     * Test of write method, of class SIM.
     */
    @Test
    public void testWrite1() {
//        System.out.println("write: ");
        String expResult = "Test 1#441234567890#United Kingdom#O2#Pay As You Go#7270#100.0#false#false#To be destroyed";
        String result = sim1.write2DB();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class SIM.
     */
    @Test
    public void testWrite2() {
//        logic.SIM instance = new logic.SIM();
        String expResult = "Test 2#631234567890#Philippines#Smart#Contract#1234#50.0#true#false#Cross Check Account";
        String result = sim2.write2DB();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class SIM.
     */
    @Test
    public void testWrite3() {
//        System.out.println("write: ");
        String expResult = "Test 3#601234567890#Malaysia#MyMaxis#Monthly#4675#0.0#true#false#LOST";
        String result = sim3.write2DB();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class SIM.
     */
    @Test
    public void testWrite4() {
//        System.out.println("write: ");
        String expResult = "Test 4#101234567890#United States America#AT&T#Monthly#8768#0.0#true#true#Personal";
        String result = sim4.write2DB();
        assertEquals(expResult, result);
        fail("Change REQUIRED to compensate In Use parameter");
    }
    @org.junit.jupiter.api.Test
    void getSimName() {
    }
}