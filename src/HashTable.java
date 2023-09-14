/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-08-24
 * 
 *        An extensible, closed hash table
 * 
 */
public class HashTable {
    /**
     * The value used to replace a deleted record
     */
    static final Record TOMBSTONE = new Record(-1, null);
    private Record[] table;
    private int size;
    private int capacity;
    private int threshold;

    /**
     * Hash Table constructor
     * 
     * @param size
     *            Number of slots for the hash table, a power of two
     */
    public HashTable(int size) {
        this.capacity = size;
        this.table = new Record[size];
        this.size = 0;
        this.threshold = (int)(capacity / 2);
    }


    /**
     * Used to insert a memory handle into the hash table
     * Uses single and double hashing functionality
     * 
     * @param record
     *            An object containing reference to the memHandle
     * @return Returns true if successfully inserted into the hash table
     */
    public boolean insert(Record record) {
        // Makes half the list is not filled
        // If filled, call the resize function to double the capacity
        if (size == threshold) {
            resize();
        }
        // Collect the primary hash value based on key
        int index = hash(record.getKey());

        // Collect the step size from double hash value
        int stepSize = doubleHash(record.getKey());

        // Checking to see if there is a collision
        // Only inserts in the index position if the value is null or TOMBSTONE
        if (search(record.getKey()) == null) {
            while (table[index] != null) { // && table[index] != TOMBSTONE) {
                // If the hash value is taken, continue to step the index
                index = (index + stepSize) % capacity;
            }
            table[index] = record;
            size++;
            return true;
        }
        else {
            return false;
        }

    }


    /**
     * Searches the hash table for the specified key
     * 
     * @param key
     *            An integer used for the handle ID
     * @return MemHandle
     *         The handle containing the seminar reference
     */
    public Record search(int key) {
        // Collect primary hash and step size
        int index = hash(key);
        int stepSize = doubleHash(key);

        // If primary hash is null, value does not exist
        // System.out.printf("Starting search for key: %d%n", key);
        while (table[index] != null) {
            // System.out.println(table[index].position);
            // Makes sure the value is not a TOMBSTONE
            if (/* table[index].getKey() != -1 && */table[index]
                .getKey() == key) {
                return table[index];
            }
            index = (index + stepSize) % capacity;
        }
        return null;
    }


    /**
     * Deletes the specific seminar reference from the hash table
     * 
     * @param key
     *            An integer used for the handle ID
     * @return boolean Returns true if the handle was successfully deleted
     */
    public Record delete(int key) {

        // Collects the primary index and stepSize
        int index = hash(key);
        int stepSize = doubleHash(key);

        // Value mustn't be null to delete
        while (table[index] != null) {
            // if the value is not a TOMBSTONE and the position is the key
            if (table[index].getKey() == key ) {
                Record record = table[index];
                table[index] = TOMBSTONE;
                size--;
                return record;
            }
            index = (index + stepSize) % capacity;
        }
        return null;
    }


    /**
     * Prints all contents in the hash table
     * Iterates linearly
     */
    public void printHashTable() {
        System.out.printf("Hashtable:%n");
        for (int i = 0; i < table.length; i++) {
            // If value exists
            if (table[i] != null) {
                // If value is equal to the TOMBSTONE but is not a true value
                if ((table[i].getKey() == -1)) {
                    System.out.printf("%d: TOMBSTONE%n", i);
                }
                // Printing the key of the value (memHandle)
                else {
                    System.out.printf("%d: %d%n", i, table[i].getKey());
                }
            }
        }
        System.out.printf("total records: %d%n", size);
    }


    private void resize() {
        // Double the capacity
        capacity *= 2;
        // Double the threshold
        threshold = (int)(capacity / 2);

        // Creating a newTable with size of new capacity
        Record[] newTable = new Record[capacity];
        // Iterate through all the slots of the og hash table
        for (Record value : table) {
            // If the value exits
            if (value != null && value != TOMBSTONE) {
                // Collect new hash and step values to reinsert all old handles
                // into the new table
                int index = hash(value.getKey());
                int stepSize = doubleHash(value.getKey());

                // If there is a collision in the new hash table
                while (newTable[index] != null) {
                    index = (index + stepSize) % capacity;
                }
                // set the memHandle to the new hash value in the new table
                newTable[index] = value;
            }
        }
        // replace the old table with the new table
        table = newTable;
        System.out.printf("Hash table expanded to %d records%n", capacity);
    }


    private int hash(int key) {
        return key % capacity;
    }


    private int doubleHash(int key) {
        return (((int)(key / capacity) % (capacity / 2)) * 2) + 1;
    }


    /**
     * 
     * @return threshold
     */
    public int getThreshold() {
        return threshold;
    }


    /**
     * 
     * @return size
     */
    public int getCapacity() {
        return capacity;
    }
}
