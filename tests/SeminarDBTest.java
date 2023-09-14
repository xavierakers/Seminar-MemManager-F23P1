import java.util.ArrayList;
import java.util.Arrays;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-01
 * 
 *        SemianrDB Tests
 * 
 */
public class SeminarDBTest extends TestCase {
    private SeminarDB controller;
    /**
     * List to hold seminar data for convenience
     */
    private ArrayList<String> semData;

    /**
     * Test controller deletion
     * 
     * @throws Exception
     */
    public void testDeleteSem() throws Exception {
        controller = new SeminarDB(4, 4);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present"
                + " an overview of HCI research at VT", "HCI"));

        controller.insertSem(semData);
        assertNotNull(controller.searchSem("1"));
        controller.deleteSem("1");
        assertNull(controller.searchSem("1"));
    }


    /**
     * Tests for controller deletion
     * 
     * @throws Exception
     *             Seminar serialization
     */
    public void testDeleteSem2() throws Exception {
        controller = new SeminarDB(4, 4);

        assertFalse(controller.deleteSem("1"));
    }


    /**
     * Testing deletion of a sem that exist and does not exist
     * 
     * @throws Exception
     */
    public void testDeleteSem3() throws Exception {
        controller = new SeminarDB(4, 4);

        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present"
                + " an overview of HCI research at VT", "HCI"));

        controller.insertSem(semData);
        assertNotNull(controller.searchSem("1"));
        assertNotNull(controller.deleteSem("1"));
        assertNull(controller.searchSem("1"));

        assertFalse(controller.deleteSem("2"));
        assertNull(controller.searchSem("2"));
    }


    /**
     * Test for controller search
     * 
     * @throws Exception
     *             Seminar serialization
     */
    public void testSearchSem() throws Exception {
        controller = new SeminarDB(4, 4);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present an overview of HCI research at VT",
            "HCI"));

        controller.insertSem(semData);

        assertNotNull(controller.searchSem("1"));
    }


    /**
     * Test controller duplication prevention
     * 
     * @throws Exception
     *             Seminar serialization
     */
    public void testDuplicateInsert() throws Exception {
        controller = new SeminarDB(256, 4);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present an overview of HCI research at VT",
            "HCI"));

        controller.insertSem(semData);
        assertEquals(controller.insertSem(semData), 0);
    }


    /**
     * Tests for controller insertion
     * 
     * @throws Exception
     *             Seminar serialization
     */
    public void testInsert() throws Exception {
        controller = new SeminarDB(4, 4);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present an overview of HCI research at VT",
            "HCI", "Computer_Science", "VT", "Virginia_Tech"));
        System.out.println(controller.insertSem(semData));
        // assertTrue(controller.insertSem(semData));
        controller.printBlocks();
    }


    /**
     * Complex insertion
     */
    public void testComplexInsertDB() {
        controller = new SeminarDB(256, 16);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present an overview of HCI research at VT",
            "HCI", "Computer_Science", "VT", "Virginia_Tech"));
    }


    /**
     * Testing success and failed delete
     * 
     * @throws Exception
     */
    public void testDeletions() throws Exception {
        controller = new SeminarDB(256, 16);
        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present an overview of HCI research at VT",
            "HCI", "Computer_Science", "VT", "Virginia_Tech"));

        assertNotSame(controller.insertSem(semData), 0);
        assertTrue(controller.deleteSem("1"));
        assertFalse(controller.deleteSem("2"));
    }
}
