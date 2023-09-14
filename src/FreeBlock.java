/**
 * @author Xavier Akers
 * 
 * @version 2.0
 * 
 * @since 2023-09-07
 * 
 *        FreeBlock Linked List
 */
class FreeBlock {
    private int position;
    private int size;
    private FreeBlock next;
    private FreeBlock prev;

    /**
     * Constructor
     * 
     * @param position
     *            location within the memory pool
     * @param size
     *            the true size of the record
     */
    public FreeBlock(int position, int size) {
        this.position = position;
        this.size = size;
        this.next = null;
        this.prev = null;
    }


    /**
     * @return Returns the position
     */
    public int getPosition() {
        return position;
    }


    /**
     * Sets the position
     * 
     * @param position
     *            Position within the byte array
     */
    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * @return Returns the true size
     */
    public int getSize() {
        return size;
    }


    /**
     * Sets the size
     * 
     * @param size
     *            Size within the byte array
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * @return Returns the linked block
     */
    public FreeBlock getNext() {
        return next;
    }


    /**
     * Sets the linked block
     * 
     * @param next
     *            The next freeBlock
     */
    public void setNext(FreeBlock next) {
        this.next = next;
    }


    /**
     * 
     * @return return the previous linked block
     */
    public FreeBlock getPrev() {
        return prev;
    }


    /**
     * Return the previous node
     * 
     * @param prev
     *            The previous freeblock
     */
    public void setPrev(FreeBlock prev) {
        this.prev = prev;
    }
}