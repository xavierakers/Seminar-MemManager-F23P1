import java.util.Random;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-01
 * 
 *        Hash Table Test
 * 
 */
public class HashTableTest extends TestCase {
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
        //table.insert(record2);
        //table.insert(record2);
    }
    
    /**
     * Testing insert probe sequence mutation
     */
    public void testInsertProbe() {
        HashTable table = new HashTable(8);
        Record record1 = new Record(1, null);
        Record record2 = new Record(9, null);
        Record record3 = new Record(17, null);
        
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));
        
        assertEquals(table.search(record2.getKey()), record2);
        assertEquals(table.search(record3.getKey()), record3);
    }
    
    /**
     * Deleting record that does not exist
     */
    public void testNullDelete() {
        HashTable table = new HashTable(8);
        Record record1 = new Record(1, null);
        
        assertTrue(table.insert(record1));
        assertEquals(table.delete(record1.getKey()), record1);
        assertNull(table.delete(2));
        
    }
    
    /**
     * Insert, delete, reinsert
     */
    public void testTombstoneInsert() {
        HashTable table = new HashTable(16);
        Record record1 = new Record(1, null);
        
        assertTrue(table.insert(record1));
        assertEquals(table.delete(record1.getKey()), record1);
        assertTrue(table.insert(record1));
        assertEquals(table.search(record1.getKey()), record1);
    }
    /**
     * More complex insert, delete, reinserts
     */
    public void testTombstoneInsert2() {
        HashTable table = new HashTable(16);
        Record record1 = new Record(1, null);
        Record record2 = new Record(2, null);
        Record record3 = new Record(3, null);
        Record record4 = new Record(17, null);
        Record record5 = new Record(18, null);
        
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));
        assertTrue(table.insert(record4));
        assertEquals(table.delete(record1.getKey()), record1);
        assertTrue(table.insert(record5));
        assertEquals(table.delete(record3.getKey()), record3);
        assertEquals(table.delete(record4.getKey()), record4);
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record4));
        
    }
}
