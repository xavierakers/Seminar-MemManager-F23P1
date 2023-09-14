import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-08-24
 * 
 *        Handles communication between the commandProcessor, HashTable, and
 *        memManager(not yet implemented)
 * 
 */
public class SeminarDB {
    private MemManager memManager;
    private HashTable table;

    /**
     * Creating the table and manager
     * memManager = new MemManager(memSize);
     * 
     * @param memSize
     *            Bytes to be allocated, power of 2
     * @param hashSize
     *            Slots to be allocated, power of 2
     */
    public SeminarDB(int memSize, int hashSize) {

        memManager = new MemManager(memSize);
        table = new HashTable(hashSize);

    }


    /**
     * Creates a seminar, sends to hash table to insert
     * 
     * @param input
     *            ArrayList containing data from the input file
     * @return Returns true if successfully inserted
     * @throws Exception
     *             Serialization
     */
    public int insertSem(ArrayList<String> input) throws Exception {

        // Extracting all the data from the cmd processor
        // Converting to proper variable type
        int id = Integer.parseInt(input.get(0).trim());
        String title = input.get(1);
        String date = (String)input.get(2);
        int length = Integer.parseInt(input.get(3).trim());

        Short x = Short.parseShort(input.get(4).trim());
        Short y = Short.parseShort(input.get(5).trim());
        int cost = Integer.parseInt(input.get(6));

        String desc = input.get(7);
        String[] keywords = new String[1];
        // Keywords were stored at the end of the arrayList
        for (int i = 8, j = 0; i < input.size(); i++) {
            if (j == keywords.length) {
                keywords = Arrays.copyOf(keywords, keywords.length + 1);
            }
            keywords[j++] = input.get(i);
        }

        Seminar sem = new Seminar(id, title, date, length, x, y, cost, keywords,
            desc);

        // MemHandle object will be updated once memManager is implemented
        // MemHandle currently stores the seminar
        if (table.search(id) == null) {
            MemHandle handle = memManager.insert(sem.serialize(), sem
                .serialize().length);
            Record record = new Record(id, handle);

            table.insert(record);
            // System.out.printf("Successfully inserted record with ID %d%n",
            // id);
            // System.out.println(sem);
            // System.out.println("Size: " + sem.serialize().length);
            return sem.serialize().length;
        }
        else {
            // System.out.printf(
            // "Insert FAILED - There is already a record with ID %d%n", id);
            return 0;
        }
    }


    /**
     * Deletes a seminar from the hash table and memory block
     * 
     * @param id
     *            The key used to find reference in hash table
     * @return Returns true if successfully deleted
     */
    public boolean deleteSem(String id) {
        // Converting to proper primitive type
        int key = Integer.parseInt(id);

        // Calls hash table function
        Record record = table.delete(key);
        if (record != null) {

            memManager.remove(record.getHandle());
            System.out.printf(
                "Record with ID %d successfully deleted from the database%n",
                key);
            return true;
        }
        else {
            System.out.printf(
                "Delete FAILED -- There is no record with ID %d%n", key);
            return false;
        }

    }


    /**
     * Finds seminar information
     * 
     * @param id
     *            Reference to find hash table reference
     * @return Returns true if found successfully
     * @throws Exception
     */
    public Seminar searchSem(String id) throws Exception {

        // Converting to proper primitive type
        int key = Integer.parseInt(id);
        Record record = table.search(key);
        if (record != null) {
            byte[] buff = new byte[record.getHandle().getLength()];

            memManager.get(buff, record.getHandle());
            Seminar sem = Seminar.deserialize(buff);

            // System.out.printf("Found record with ID %d:%n", key);
            // System.out.println(sem);
            return sem;
        }
        else {
            // System.out.printf(
            // "Search FAILED -- There is no record with ID %d%n", key);
            return null;
        }

    }


    /**
     * Commands hash table to print its contents
     * 
     * @return Returns true if successfully printed
     */
    public boolean printHashTable() {
        table.printHashTable();
        return true;
    }


    /**
     * Commands memory manager to print its free blocks
     * 
     * @return Returns true if successfully printed
     */
    public boolean printBlocks() {
        memManager.dump();
        return true;
    }

}
