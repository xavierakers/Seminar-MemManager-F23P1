/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-12
 * 
 *        Custom record for Hash table
 * 
 */
public class Record {

    private int key;
    private MemHandle handle;

    /**
     * Constructor
     * 
     * @param key
     *            The ID
     * @param handle
     *            Reference to memory
     */
    public Record(int key, MemHandle handle) {
        this.key = key;
        this.handle = handle;
    }


    /**
     * @return the handle
     */
    public MemHandle getHandle() {
        return handle;
    }


    /**
     * @param handle
     *            the handle to set
     */
    public void setHandle(MemHandle handle) {
        this.handle = handle;
    }


    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }


    /**
     * @param key
     *            the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }
}
