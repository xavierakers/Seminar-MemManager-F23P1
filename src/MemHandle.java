/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-08-24
 * 
 *        Handle Object
 * 
 */
class MemHandle {

    private int position;
    private int length;

    /**
     * Constructor
     * 
     * @param position
     *            The ID for the reference
     * @param length
     *            The number of bytes being referenced
     */
    public MemHandle(int position, int length) {
        this.position = position;
        this.length = length;

    }


    /**
     * 
     * @return Returns the (int) position ID
     */
    public int getPosition() {
        return position;
    }


    /**
     * 
     * @return Returns the (int) byte length
     */
    public int getLength() {
        return length;
    }

}
