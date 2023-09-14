import student.TestCase;

/**
 * @author Xavier Akers
 * @version 1.0
 */
public class SemManagerTest extends TestCase {

    /*
     * public void testMInitx() {
     * SemManager sem = new SemManager();
     * assertNotNull(sem);
     * SemManager.main(null);
     * }
     */

    /**
     * Tests basic run and input
     */
    public void testMain1() {
        SemManager.main(new String[] { "1024", "1024",
            "testFiles/testMain.txt" });
        String output = systemOut().getHistory();
        assertEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an"
            + " overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 173\n", output);

    }

}
