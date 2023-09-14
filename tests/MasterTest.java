import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-01
 * 
 *        Contains all tests cases to run proper mutation testing
 * 
 */
public class MasterTest extends TestCase {
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

    private SeminarDB controller = new SeminarDB(1024, 1024);
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Test insert command processing
     */
    public void testInsert1() {
        cp.readCommands("testFiles/testInsert.txt");
        String output = systemOut().getHistory();
        assertEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an"
            + " overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 173\r\n" + "Successfully inserted"
            + " record with ID 2\r\n"
            + "ID: 2, Title: Computational Biology and"
            + " Bioinformatics in CS at Virginia Tech\r\n"
            + "Date: 0610071600, Length: 60, X: 20, Y: 10," + " Cost: 30\r\n"
            + "Description: Introduction to   bioinformatics"
            + " and computation biology\r\n"
            + "Keywords: Bioinformatics, computation_biology"
            + ", Biology, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 244\r\n" + "Successfully inserted"
            + " record with ID 3\r\n"
            + "ID: 3, Title: Computing Systems Research at VT\r\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
            + "Description: Seminar about the      "
            + "Computing systems research at      VT\r\n"
            + "Keywords: high_performance_computing, grids,"
            + " VT, computer, science\r\n" + "Size: 192\n", output);
    }


    /**
     * Test search command processing
     */
    public void testSearch1() {
        cp.readCommands("testFiles/testSearch.txt");
        String output = systemOut().getHistory();
        assertEquals("Search FAILED -- There is no record with ID 1\n", output);
    }


    /**
     * Test delete command processing
     */
    public void testDelete1() {
        cp.readCommands("testFiles/testDelete.txt");
        String output = systemOut().getHistory();
        assertEquals("Delete FAILED -- There is no record with ID 1\n", output);
    }


    /**
     * Test print hash command processing
     */
    public void testPrintHash() {
        cp.readCommands("testFiles/testPrintHash.txt");
        String output = systemOut().getHistory();
        assertEquals("Hashtable:\n" + "total records: 0\n", output);
    }


    /**
     * Test print blocks command processing
     */
    public void testPrintBlocks() {
        cp.readCommands("testFiles/testPrintBlocks.txt");
        String output = systemOut().getHistory();
        assertEquals("Freeblock List:\r\n" + "1024: 0\n", output);
    }


    /**
     * Test invalid command processing
     */
    public void testInvalidCommand() {
        cp.readCommands("testFiles/testInvalidCommand.txt");
        String output = systemOut().getHistory();
        assertEquals("Invalid command\n", output);
    }


    /**
     * Test ignoring newline characters
     */
    public void testRandomInputNewlines() {
        cp.readCommands("testFiles/testRandomInputNewlines.txt");
        String output = systemOut().getHistory();
        assertEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will"
            + " present an overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 173\r\n" + "Successfully inserted record with ID 2\r\n"
            + "ID: 2, Title: Computational Biology "
            + "and Bioinformatics in CS at Virginia Tech\r\n"
            + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\r\n"
            + "Description: Introduction to   "
            + "bioinformatics and computation biology\r\n"
            + "Keywords: Bioinformatics, computation_biology,"
            + " Biology, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 244\r\n" + "Successfully inserted record with ID 3\r\n"
            + "ID: 3, Title: Computing Systems Research at VT\r\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
            + "Description: Seminar about the"
            + "      Computing systems research at      VT\r\n"
            + "Keywords: high_performance_computing,"
            + " grids, VT, computer, science\r\n" + "Size: 192\r\n"
            + "Insert FAILED - There is already a record with ID 3\n", output);
    }


    /**
     * Testing if a file is empty
     */
    public void testEmptyInput() {
        cp.readCommands("testFiles/testNullInput.txt");
        String out = systemOut().getHistory();
        assertEquals("", out);
    }

    private SeminarDB controller1;
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
        controller = new SeminarDB(256, 4);

        semData = new ArrayList<String>(Arrays.asList("1",
            "Overview of HCI Research at VT", "0610051600", "90", "10", "10",
            "45", "Thisseminar will present"
                + " an overview of HCI research at VT", "HCI"));

        assertNotNull(controller.insertSem(semData));
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
     * hash table size used for consistent testing
     */
    private static final int HASHTABLE_SIZE = 256;

    /**
     * Test hash table record inserts and search
     */
    public void testInsertAndSearch1() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);

        table.insert(record1);
        table.insert(record2);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));

    }


    /**
     * More tests for hash table inserts and search
     */
    public void testInsertAndSearch2() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(10, null);
        Record record3 = new Record(56, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
    }


    /**
     * More tests for hash table inserts and search
     * Keys exceed hash table size
     */
    public void testInsertAndSearch3() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1024, null);
        Record record2 = new Record(5000, null);
        Record record3 = new Record(1, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
    }


    /**
     * 3 records where there are random keys and random sizes
     * Sizes will not go over 85
     * Not testing the Resize functionality
     */
    public void testInsertAndSearch4() {
        Random rand = new Random();
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(Math.abs(rand.nextInt()), null);
        Record record2 = new Record(Math.abs(rand.nextInt()), null);
        Record record3 = new Record(Math.abs(rand.nextInt()), null);
        System.out.println(record1.getKey());
        System.out.println(record2.getKey());
        System.out.println(record3.getKey());
        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));

    }


    /**
     * Test inserts, deletions, and re-insertion
     */
    public void testInsertDeleteInsert() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);
        Record record3 = new Record(3, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.printHashTable();

        table.delete(record2.getKey());
        assertEquals(null, table.search(record2.getKey()));
        table.printHashTable();

        table.insert(record2);
        table.printHashTable();
        assertEquals(record2, table.search(record2.getKey()));
    }


    /**
     * More tests for deletions
     * Keys exceed hash table size
     */
    public void testInsertDeleteInsert2() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(6, null);
        Record record3 = new Record(90, null);
        Record record4 = new Record(346, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.printHashTable();

        table.delete(record3.getKey());
        assertEquals(null, table.search(record3.getKey()));
        table.printHashTable();

        table.insert(record4);
        table.printHashTable();
        assertEquals(record4, table.search(record4.getKey()));
    }


    /**
     * More tests for deletions
     * Requiring double hashing and probing
     */
    public void testInsertDeleteInsert3() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(6, null);
        Record record3 = new Record(90, null);
        Record record4 = new Record(346, null);
        Record record5 = new Record(262, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.printHashTable();

        table.delete(record3.getKey());
        assertEquals(null, table.search(record3.getKey()));
        table.printHashTable();

        table.insert(record4);
        table.printHashTable();
        assertEquals(record4, table.search(record4.getKey()));

        table.delete(record2.getKey());
        assertEquals(null, table.search(record2.getKey()));

        table.insert(record5);
        assertEquals(record5, table.search(record5.getKey()));
    }


    /**
     * Basic Test for double hashing
     */
    public void testInsertAndSearchDoubleHash1() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(0, null);
        Record record2 = new Record(256, null);

        table.insert(record1);
        table.insert(record2);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
    }


    /**
     * More tests for insertions and deletions
     * 3 records with equivalent initial hash value
     */
    public void testInsertAndSearchDoubleHash2() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(0, null);
        Record record2 = new Record(256, null);
        Record record3 = new Record(512, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
    }


    /**
     * More tests for double hashing insertions and deletions
     * 3 records
     * first 2 records will have the same initial hash value
     * 3rd record will have the initial hash value of the index of the 2nd
     * record
     * 
     */
    public void testInsertAndSearchDoubleHash3() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(20, null);
        Record record2 = new Record(276, null);
        Record record3 = new Record(21, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));

    }


    /**
     * More tests for double hashing deletion and insertions
     * 4 records
     * First 2 records will have the same initial hash value
     * 3rd record will have its own initial hash value
     * 4th record will have same initial hash value as the 3rd and its first
     * step will cause a collision forcing another step
     */
    public void testInsertAndSearchDoubleHash4() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(267, null);
        Record record2 = new Record(20, null);
        Record record3 = new Record(271, null);
        Record record4 = new Record(15, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.insert(record4);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
        assertEquals(record4, table.search(record4.getKey()));
    }


    /**
     * Basic test for deletions
     */
    public void testDelete() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);
        Record record3 = new Record(3, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        table.delete(record2.getKey());
        System.out.println(table.search(record2.getKey()));
        assertEquals(null, table.search(record2.getKey()));
    }


    /**
     * Test for deletion requiring double hashing
     */
    public void testDelete2() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(257, null);
        Record record3 = new Record(3, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        table.delete(record2.getKey());
        System.out.println(table.search(record2.getKey()));
        assertEquals(null, table.search(record2.getKey()));
    }


    /**
     * Basic Test for printing hash table contents
     * Insert one record, print one record
     */
    public void testPrintHashTable1() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);

        table.insert(record1);

        table.printHashTable();

        String output = systemOut().getHistory();
        assertEquals("Hashtable:\r\n" + "1: 1\r\n" + "total records: 1\n",
            output);

    }


    /**
     * Printing more hash table contents
     * Tests for tomb stone value
     */
    public void testPrintHashTable2() {
        HashTable table = new HashTable(HASHTABLE_SIZE);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);
        Record record3 = new Record(3, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);

        table.delete(record2.getKey());

        table.printHashTable();

        String output = systemOut().getHistory();
        assertEquals("Hashtable:\r\n" + "1: 1\r\n" + "2: TOMBSTONE\r\n"
            + "3: 3\r\n" + "total records: 2\n", output);

    }


    /**
     * Basic test for resizing hash table size
     */
    public void testResize() {
        HashTable table = new HashTable(4);
        Record record1 = new Record(1, null);
        Record record2 = new Record(9, null);
        Record record3 = new Record(2, null);
        Record record4 = new Record(10, null);
        assertEquals(2, table.getThreshold());
        assertEquals(4, table.getCapacity());
        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.insert(record4);

        assertEquals(4, table.getThreshold());
        assertEquals(8, table.getCapacity());
        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
        assertEquals(record4, table.search(record4.getKey()));
    }


    /**
     * Test for resizing requiring more double hashing
     */
    public void testResize2() {
        HashTable table = new HashTable(2);
        Record record1 = new Record(1, null);
        Record record2 = new Record(9, null);
        Record record3 = new Record(2, null);
        Record record4 = new Record(10, null);

        table.insert(record1);
        table.insert(record2);
        table.insert(record3);
        table.insert(record4);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
        assertEquals(record4, table.search(record4.getKey()));
    }


    /**
     * Tests for empty hash table
     */
    public void testNullSeach() {
        HashTable table = new HashTable(4);

        assertEquals(null, table.search(1));
    }


    /**
     * HashTable threshold check
     */
    public void testThreshold() {
        HashTable table = new HashTable(4);
        assertEquals(2, table.getThreshold());
    }


    /**
     * Test Expanding table with hash table
     */
    public void testTombstoneResize() {
        HashTable table = new HashTable(4);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);
        Record record3 = new Record(3, null);
        Record record4 = new Record(4, null);

        table.insert(record1);
        table.insert(record2);

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record2, table.search(record2.getKey()));

        table.delete(record2.getKey());
        table.printHashTable();
        assertNull(table.search(record2.getKey()));

        table.insert(record3);
        table.insert(record4);
        table.printHashTable();

        assertEquals(record1, table.search(record1.getKey()));
        assertEquals(record3, table.search(record3.getKey()));
        assertEquals(record4, table.search(record4.getKey()));
        assertNull(table.search(record2.getKey()));

    }


    /**
     * Basic Initialization
     */
    public void testMemManager() {
        MemManager mem = new MemManager(256);
        mem.dump();
        assertEquals("Freeblock List:\r\n" + "256: 0\n", systemOut()
            .getHistory());
    }


    /**
     * Testing basic insertion and search
     * Inserts byte[] with size 11
     */

    public void testInsertMem1() {
        MemManager mem = new MemManager(256);

        String s = "Hello World!";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());
        MemHandle handle = mem.insert(buff, s.length());

        byte[] get = new byte[256];
        int howMuch = mem.get(get, handle);

        String s2 = new String(get, 0, howMuch);
        System.out.println(s2);
        assertTrue(s.equals(s2));
    }


    /**
     * Uses whole memory pool
     */
    public void testInsert2() {
        MemManager mem = new MemManager(256);

        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor incididunt ut labore et dolore"
            + " magna aliqua. Ut enim ad minim veniam, quis"
            + " nostrud exercitation ullamco laboris nisi ut"
            + " aliquip ex ea commodo consequat.";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());
        MemHandle handle = mem.insert(buff, s.length());

        byte[] get = new byte[256];
        int howMuch = mem.get(get, handle);

        String s2 = new String(get, 0, howMuch);

        mem.dump();
        assertTrue(s.equals(s2));
    }


    /**
     * Splits memory pool
     * Inserts byte[](128) half the size of the memory pool (256)
     */
    public void testInsert3() {
        MemManager mem = new MemManager(256);

        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor incididunt ut labore et dolore"
            + " magna aliqua. Ut e";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());
        MemHandle handle = mem.insert(buff, s.length());

        byte[] get = new byte[256];
        int howMuch = mem.get(get, handle);

        String s2 = new String(get, 0, howMuch);

        mem.dump();
        assertTrue(s.equals(s2));
    }


    /**
     * Testing insert where there is more than 2 freeBlock in the LList
     */
    public void testInsert4() {
        MemManager mem = new MemManager(256);
        byte[] buff = new byte[256];

        String s1 = "Hello World!";
        String s2 = "Lorem ipsum dolor sit amet, co";
        String s3 = "Lorem ipsum dolor sit amet, co";
        String s4 = "Lorem ipsum dolor sit amet, co";
        String s5 = "Lorem ipsum dolor sit amet, co";
        String s6 = "Lorem ipsum dolor sit amet, co";

        mem.dump();

        System.out.printf("insert handle1 %d%n", s1.length());
        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());
        mem.dump();

        System.out.printf("insert handle2 %d%n", s2.length());
        System.arraycopy(s2.getBytes(), 0, buff, 0, s2.length());
        MemHandle handle2 = mem.insert(buff, s2.length());
        mem.dump();

        System.out.printf("insert handle3 %d%n", s3.length());
        System.arraycopy(s3.getBytes(), 0, buff, 0, s3.length());
        MemHandle handle3 = mem.insert(buff, s3.length());
        mem.dump();

        System.out.printf("insert handle4 %d%n", s4.length());
        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        MemHandle handle4 = mem.insert(buff, s4.length());
        mem.dump();

        System.out.printf("insert handle5 %d%n", s5.length());
        System.arraycopy(s5.getBytes(), 0, buff, 0, s5.length());
        MemHandle handle5 = mem.insert(buff, s5.length());
        mem.dump();

        System.out.printf("insert handle6 %d%n", s6.length());
        System.arraycopy(s6.getBytes(), 0, buff, 0, s6.length());
        MemHandle handle6 = mem.insert(buff, s6.length());
        mem.dump();

        // Removal
        System.out.printf("removing handle2 %d%n", handle2.getLength());
        mem.remove(handle2);
        mem.dump();

        System.out.printf("removing handle4 %d%n", handle4.getLength());
        mem.remove(handle4);
        mem.dump();

        System.out.printf("removing handle6 %d%n", handle6.getLength());
        mem.remove(handle6);
        mem.dump();

        System.out.printf("insert handle4 %d%n", s4.length());
        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        handle4 = mem.insert(buff, s4.length());
        mem.dump();

        System.out.printf("removing handle1 %d%n", handle1.getLength());
        mem.remove(handle1);
        mem.dump();

        System.out.printf("removing handle3 %d%n", handle3.getLength());
        mem.remove(handle3);
        mem.dump();

        System.out.printf("removing handle5 %d%n", handle5.getLength());
        mem.remove(handle5);
        mem.dump();

        System.out.printf("removing handle4 %d%n", handle4.getLength());
        mem.remove(handle4);
        mem.dump();
    }


    /**
     * 
     */
    public void testdump() {
        MemManager mem = new MemManager(256);
        String s = "Hello World!";
        String s1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());
        MemHandle handle1 = mem.insert(buff, s.length());
        MemHandle handle2 = mem.insert(buff, s.length());
        System.arraycopy(s1.getBytes(), 0, buff, 0, s.length());
        MemHandle handle3 = mem.insert(buff, s1.length());

        mem.dump();
        assertEquals("Freeblock List:\r\n" + "32: 32\r\n" + "64: 64\n",
            systemOut().getHistory());

    }


    /**
     * Dumping an Empty List
     */
    public void testEmptyDump() {
        MemManager mem = new MemManager(128);
        String s1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor";
        byte[] buff = new byte[256];
        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());

        mem.dump();

        assertEquals("Freeblock List:\r\n"
            + "There are no freeblocks in the memory pool\n", systemOut()
                .getHistory());
    }


    /**
     * Dumping when there is a chain of freeBlocks in a row
     */
    public void testComplexDump() {
        /**
         * More complex removal test
         */
        MemManager mem = new MemManager(256);
        byte[] buff = new byte[256];

        String s1 = "Hello World!";
        String s2 = "Lorem ipsum dolor sit amet, co";
        String s3 = "Lorem ipsum dolor sit amet, co";
        String s4 = "Lorem ipsum dolor sit amet, co";
        String s5 = "Lorem ipsum dolor sit amet, co";
        String s6 = "Lorem ipsum dolor sit amet, co";

        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());

        System.arraycopy(s2.getBytes(), 0, buff, 0, s2.length());
        MemHandle handle2 = mem.insert(buff, s2.length());

        System.arraycopy(s3.getBytes(), 0, buff, 0, s3.length());
        MemHandle handle3 = mem.insert(buff, s3.length());

        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        MemHandle handle4 = mem.insert(buff, s4.length());

        System.arraycopy(s5.getBytes(), 0, buff, 0, s5.length());
        MemHandle handle5 = mem.insert(buff, s5.length());

        System.arraycopy(s6.getBytes(), 0, buff, 0, s6.length());
        MemHandle handle6 = mem.insert(buff, s6.length());

        // Removal
        mem.remove(handle2);
        mem.remove(handle4);
        mem.remove(handle6);
        mem.dump();
        // mem.remove(handle1);
        // mem.remove(handle3);
        // mem.dump();
        // mem.remove(handle5);
        // mem.dump();

        assertEquals("Freeblock List:\r\n" + "16: 16\r\n" + "32: 32 96 160\r\n"
            + "64: 192\n", systemOut().getHistory());

    }


    /**
     * Basic Removal Test
     */
    public void testRemove1() {
        MemManager mem = new MemManager(256);
        String s = "Hello World!";
        String s1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());

        MemHandle handle1 = mem.insert(buff, s.length());

        MemHandle handle2 = mem.insert(buff, s.length());

        System.arraycopy(s1.getBytes(), 0, buff, 0, s.length());
        MemHandle handle3 = mem.insert(buff, s1.length());

        mem.remove(handle2);

        mem.remove(handle3);

        mem.remove(handle1);
        mem.dump();

        assertEquals("Freeblock List:\r\n" + "256: 0\n", systemOut()
            .getHistory());
    }


    /**
     * More complex removal test
     */
    public void testremove2() {
        MemManager mem = new MemManager(256);
        byte[] buff = new byte[256];

        String s1 = "Hello World!";
        String s2 = "Lorem ipsum dolor sit amet, co";
        String s3 = "Lorem ipsum dolor sit amet, co";
        String s4 = "Lorem ipsum dolor sit amet, co";
        String s5 = "Lorem ipsum dolor sit amet, co";
        String s6 = "Lorem ipsum dolor sit amet, co";

        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());

        System.arraycopy(s2.getBytes(), 0, buff, 0, s2.length());
        MemHandle handle2 = mem.insert(buff, s2.length());

        System.arraycopy(s3.getBytes(), 0, buff, 0, s3.length());
        MemHandle handle3 = mem.insert(buff, s3.length());

        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        MemHandle handle4 = mem.insert(buff, s4.length());

        System.arraycopy(s5.getBytes(), 0, buff, 0, s5.length());
        MemHandle handle5 = mem.insert(buff, s5.length());

        System.arraycopy(s6.getBytes(), 0, buff, 0, s6.length());
        MemHandle handle6 = mem.insert(buff, s6.length());

        // Removal
        mem.remove(handle2);

        mem.remove(handle4);

        mem.remove(handle6);

        mem.remove(handle1);
        mem.remove(handle3);

        mem.remove(handle5);
        mem.dump();

        assertEquals("Freeblock List:\r\n" + "256: 0\n", systemOut()
            .getHistory());
    }


    /**
     * Basic Merge Buddies Test
     */
    public void testMergeBuddies1() {
        MemManager mem = new MemManager(256);
        byte[] buff = new byte[256];

        String s1 = "Hello World!";
        String s2 = "Lorem ipsum dolor sit amet, co";
        String s3 = "Lorem ipsum dolor sit amet, co";
        String s4 = "Lorem ipsum dolor sit amet, co";

        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());

        System.arraycopy(s2.getBytes(), 0, buff, 0, s2.length());
        MemHandle handle2 = mem.insert(buff, s2.length());

        System.arraycopy(s3.getBytes(), 0, buff, 0, s3.length());
        MemHandle handle3 = mem.insert(buff, s3.length());

        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        MemHandle handle4 = mem.insert(buff, s4.length());

        // Removal
        mem.remove(handle3);
        mem.remove(handle4);
        mem.remove(handle2);
        mem.remove(handle1);
        mem.dump();

        assertEquals("Freeblock List:\r\n" + "256: 0\n", systemOut()
            .getHistory());

    }


    /**
     * Complex Merge Buddies Test
     * 
     * Merging with a nonfirst node
     * Inserting into a first node
     * 
     */
    public void testMergeBuddies2() {
        MemManager mem = new MemManager(256);
        byte[] buff = new byte[256];

        String s1 = "Hello";
        String s2 = "Lorem ipsum dol";
        String s3 = "Lorem ipsum dol";
        String s4 = "Lorem ipsum dol";
        String s5 = "Lorem ipsum dol";
        String s6 = "Lorem ipsum dol";
        String s7 = "Lorem ipsum dol";
        String s8 = "Lorem ipsum dol";
        String s9 = "Lorem ipsum dol";
        String s10 = "Lorem ipsum dol";
        String s11 = "Lorem ipsum dol";

        mem.dump();
        // Insertions

        System.arraycopy(s1.getBytes(), 0, buff, 0, s1.length());
        MemHandle handle1 = mem.insert(buff, s1.length());
        System.arraycopy(s2.getBytes(), 0, buff, 0, s2.length());
        MemHandle handle2 = mem.insert(buff, s2.length());

        System.arraycopy(s3.getBytes(), 0, buff, 0, s3.length());
        MemHandle handle3 = mem.insert(buff, s3.length());
        System.arraycopy(s4.getBytes(), 0, buff, 0, s4.length());
        MemHandle handle4 = mem.insert(buff, s4.length());
        System.arraycopy(s5.getBytes(), 0, buff, 0, s5.length());
        MemHandle handle5 = mem.insert(buff, s4.length());
        System.arraycopy(s6.getBytes(), 0, buff, 0, s6.length());
        MemHandle handle6 = mem.insert(buff, s4.length());
        System.arraycopy(s7.getBytes(), 0, buff, 0, s7.length());
        MemHandle handle7 = mem.insert(buff, s7.length());
        System.arraycopy(s8.getBytes(), 0, buff, 0, s8.length());
        MemHandle handle8 = mem.insert(buff, s8.length());
        System.arraycopy(s9.getBytes(), 0, buff, 0, s9.length());
        MemHandle handle9 = mem.insert(buff, s4.length());
        System.arraycopy(s10.getBytes(), 0, buff, 0, s10.length());
        MemHandle handle10 = mem.insert(buff, s10.length());
        System.arraycopy(s11.getBytes(), 0, buff, 0, s11.length());
        MemHandle handle11 = mem.insert(buff, s11.length());
        // Removal
        mem.remove(handle3);
        mem.remove(handle4);
        mem.remove(handle2);
        mem.remove(handle5);
        mem.remove(handle1);
        mem.remove(handle6);
        mem.remove(handle8);
        mem.remove(handle10);
        mem.remove(handle9);
        mem.remove(handle7);
        mem.remove(handle11);

        assertEquals("Freeblock List:\r\n" + "256: 0\n", systemOut()
            .getHistory());
    }


    /**
     * Resize functionality
     */
    public void testResizeMem() {
        MemManager mem = new MemManager(32);

        String s = "Hello World!";
        byte[] buff = new byte[256];
        System.arraycopy(s.getBytes(), 0, buff, 0, s.length());
        MemHandle handle = mem.insert(buff, s.length());
        MemHandle handle2 = mem.insert(buff, s.length());
        MemHandle handle3 = mem.insert(buff, s.length());

        byte[] get = new byte[256];
        int howMuch = mem.get(get, handle);

        String s2 = new String(get, 0, howMuch);
        assertTrue(s.equals(s2));
        mem.dump();

        assertEquals("Memory pool expanded to 64 bytes\r\n"
            + "Freeblock List:\r\n" + "16: 48\n", systemOut().getHistory());

        mem.remove(handle3);
        mem.remove(handle2);
        mem.remove(handle);
        mem.dump();
        assertEquals("Memory pool expanded to 64 bytes\r\n"
            + "Freeblock List:\r\n" + "16: 48\r\n" + "Freeblock List:\r\n"
            + "64: 0\n", systemOut().getHistory());

    }


    /**
     * A series of complex inserts
     * Inserts with different byte sizes; [8, 256]
     */
    public void testComplexInsert() {
        MemManager mem = new MemManager(512);
        String s256 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit, sed "
            + "do eiusmod tempor incididunt ut labore"
            + " et dolore magna aliqua. Ut enim ad minim veniam,"
            + " quis nostrud exercitation ullamco laboris"
            + " nisi ut aliquip ex ea commodo consequat.";
        String s128 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit, sed "
            + "do eiusmod tempor incididunt ut labore"
            + " et dolore magna aliqua.";
        String s64 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit,";
        String s32 = "Lorem ipsum dolor sit amet, con";
        String s16 = "Lorem ipsum do";
        String s8 = "Lorem i";

        byte[] buff = new byte[256];

        System.arraycopy(s256.getBytes(), 0, buff, 0, s256.length());
        MemHandle handle256 = mem.insert(buff, s256.length());

        System.arraycopy(s64.getBytes(), 0, buff, 0, s64.length());
        MemHandle handle64 = mem.insert(buff, s64.length());

        System.arraycopy(s128.getBytes(), 0, buff, 0, s128.length());
        MemHandle handle128 = mem.insert(buff, s128.length());

        System.arraycopy(s8.getBytes(), 0, buff, 0, s8.length());
        MemHandle handle8 = mem.insert(buff, s8.length());

        System.arraycopy(s16.getBytes(), 0, buff, 0, s16.length());
        MemHandle handle16 = mem.insert(buff, s16.length());

        System.arraycopy(s32.getBytes(), 0, buff, 0, s32.length());
        MemHandle handle32 = mem.insert(buff, s32.length());

        mem.dump();

        byte[] get = new byte[256];
        int howMuch;

        howMuch = mem.get(get, handle256);
        String out256 = new String(get, 0, howMuch);
        assertTrue(s256.equals(out256));

        howMuch = mem.get(get, handle128);
        String out128 = new String(get, 0, howMuch);
        assertTrue(s128.equals(out128));

        howMuch = mem.get(get, handle64);
        String out64 = new String(get, 0, howMuch);
        assertTrue(s64.equals(out64));

        howMuch = mem.get(get, handle32);
        String out32 = new String(get, 0, howMuch);
        assertTrue(s32.equals(out32));

        howMuch = mem.get(get, handle16);
        String out16 = new String(get, 0, howMuch);
        assertTrue(s16.equals(out16));

        howMuch = mem.get(get, handle8);
        String out8 = new String(get, 0, howMuch);
        assertTrue(s8.equals(out8));

        assertEquals("Freeblock List:\r\n" + "8: 328\r\n", systemOut()
            .getHistory());

    }


    /**
     * Extension of complex inserts followed by all their removals
     * Inserts with different byte sizes; [8, 256]
     */
    public void testComplexInsertRemoval() {
        MemManager mem = new MemManager(512);
        String s256 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit, sed "
            + "do eiusmod tempor incididunt ut labore"
            + " et dolore magna aliqua. Ut enim ad minim veniam,"
            + " quis nostrud exercitation ullamco laboris"
            + " nisi ut aliquip ex ea commodo consequat.";
        String s128 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit, sed "
            + "do eiusmod tempor incididunt ut labore"
            + " et dolore magna aliqua.";
        String s64 = "Lorem ipsum dolor sit amet,"
            + " consectetur adipiscing elit,";
        String s32 = "Lorem ipsum dolor sit amet, con";
        String s16 = "Lorem ipsum do";
        String s8 = "Lorem i";

        byte[] buff = new byte[256];

        System.arraycopy(s256.getBytes(), 0, buff, 0, s256.length());
        MemHandle handle256 = mem.insert(buff, s256.length());

        System.arraycopy(s64.getBytes(), 0, buff, 0, s64.length());
        MemHandle handle64 = mem.insert(buff, s64.length());

        System.arraycopy(s128.getBytes(), 0, buff, 0, s128.length());
        MemHandle handle128 = mem.insert(buff, s128.length());

        System.arraycopy(s8.getBytes(), 0, buff, 0, s8.length());
        MemHandle handle8 = mem.insert(buff, s8.length());

        System.arraycopy(s16.getBytes(), 0, buff, 0, s16.length());
        MemHandle handle16 = mem.insert(buff, s16.length());

        System.arraycopy(s32.getBytes(), 0, buff, 0, s32.length());
        MemHandle handle32 = mem.insert(buff, s32.length());

        mem.dump();

        byte[] get = new byte[256];
        int howMuch;

        howMuch = mem.get(get, handle256);
        String out256 = new String(get, 0, howMuch);
        assertTrue(s256.equals(out256));

        howMuch = mem.get(get, handle128);
        String out128 = new String(get, 0, howMuch);
        assertTrue(s128.equals(out128));

        howMuch = mem.get(get, handle64);
        String out64 = new String(get, 0, howMuch);
        assertTrue(s64.equals(out64));

        howMuch = mem.get(get, handle32);
        String out32 = new String(get, 0, howMuch);
        assertTrue(s32.equals(out32));

        howMuch = mem.get(get, handle16);
        String out16 = new String(get, 0, howMuch);
        assertTrue(s16.equals(out16));

        howMuch = mem.get(get, handle8);
        String out8 = new String(get, 0, howMuch);
        assertTrue(s8.equals(out8));

        assertEquals("Freeblock List:\r\n" + "8: 328\r\n", systemOut()
            .getHistory());

        mem.remove(handle32);
        mem.remove(handle256);
        mem.remove(handle8);
        mem.remove(handle64);
        mem.remove(handle128);
        mem.remove(handle16);
        mem.dump();
        assertEquals("Freeblock List:\r\n" + "8: 328\r\n"
            + "Freeblock List:\r\n" + "512: 0\r\n", systemOut().getHistory());
    }


    /**
     * Testing found record
     */
    public void testSearch2() {
        cp.readCommands("testFiles/testSearch2.txt");
        assertEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview "
            + "of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 173\r\n" + "Found record with ID 1:\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview of"
            + " HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n",
            systemOut().getHistory());
    }


    /**
     * Testing insertion of same ID
     */
    public void testInsertSameID() {
        HashTable table = new HashTable(4);
        Record record1 = new Record(1, null);
        Record record2 = new Record(1, null);
        Record record3 = new Record(1, null);
        Record record4 = new Record(1, null);

        assertTrue(table.insert(record1));
        assertFalse(table.insert(record1));
        // table.insert(record2);
        // table.insert(record2);
    }


    /**
     * Testing insert probe sequence mutation
     */
    public void testInsertProbe() {
        HashTable table = new HashTable(8);
        Record record1 = new Record(1, null);
        Record record2 = new Record(9, null);
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));

        assertEquals(table.search(record2.getKey()), record2);

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
