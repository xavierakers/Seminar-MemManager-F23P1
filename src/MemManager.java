import java.lang.Math;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-08-24
 * 
 *        A Memory Manager
 *        Buddy Method Functionality
 * 
 */

public class MemManager {
    private byte[] memPool;
    private FreeBlock[] freeList;
    private int size;

    /**
     * Constructor
     * 
     * @param poolSize
     *            The initial size of the byte array
     */
    public MemManager(int poolSize) {
        this.size = poolSize;
        this.memPool = new byte[poolSize];
        this.freeList = new FreeBlock[(int)(Math.log(poolSize) / Math.log(2))];
        this.freeList[this.freeList.length - 1] = new FreeBlock(0, poolSize);
    }


    /**
     * Inserts deserialized seminar data into the memoryPool
     * 
     * @param space
     *            deserialized seminar data
     * @param byteSize
     *            the size of the byte array
     * @return MemHandle
     */
    public MemHandle insert(byte[] space, int byteSize) {
        // Check if freeList is empty
        checkSize(byteSize);
        FreeBlock freeBlock = allocateBlock(byteSize);
        int blockIndex = (int)((Math.log(freeBlock.getSize()) / Math.log(2))
            - 1);
        // Removing the freeBlock from the freeList

        FreeBlock next = freeList[blockIndex];

        freeList[blockIndex] = next.getNext();
        if (freeList[blockIndex] != null) {
            freeList[blockIndex].setPrev(null);

        }

        // Copying in the data
        System.arraycopy(space, 0, memPool, freeBlock.getPosition(), byteSize);
        return new MemHandle(freeBlock.getPosition(), byteSize);
    }


    /**
     * Retrieves the byte information
     * 
     * @param space
     *            Array pass by reference; where the bytes get returned
     * @param handle
     *            The memHandle
     * @return The length of the string
     */
    public int get(byte[] space, MemHandle handle) {
        System.arraycopy(memPool, handle.getPosition(), space, 0, handle
            .getLength());
        return handle.getLength();
    }


    /**
     * Print contents of FreeList
     * 
     */
    public void dump() {
        System.out.println("Freeblock List:");
        boolean isEmpty = true;

        for (int i = 0; i < freeList.length; i++) {
            if (freeList[i] != null) {
                System.out.printf("%d: %d", (int)Math.pow(2, i + 1), freeList[i]
                    .getPosition());
                isEmpty = false;

                FreeBlock printNext = freeList[i].getNext();
                while (printNext != null) {
                    System.out.printf(" %d", printNext.getPosition());
                    printNext = printNext.getNext();
                }
                System.out.println();
            }

        }
        if (isEmpty) {
            System.out.println("There are no freeblocks in the memory pool");
        }
    }


    /**
     * Free a block at the position specified by the handle
     * Merge adjacent free blocks
     * 
     * @param handle
     *            Memory Handle
     */
    public void remove(MemHandle handle) {
        int trueBlockSize = getTrueBlockSize(handle.getLength());

        int returnIndex = (int)((Math.log(trueBlockSize) / Math.log(2)) - 1);

        FreeBlock returnBlock = new FreeBlock(handle.getPosition(),
            trueBlockSize);
        // if linked list is empty
        if (freeList[returnIndex] == null) {
            freeList[returnIndex] = returnBlock;
            return;
        }
        else { // if linked list is not empty
            FreeBlock step = freeList[returnIndex];
            do {
                if (step.getPosition() < returnBlock.getPosition()) {
                    if (step.getNext() != null) {
                        step = step.getNext();
                    }
                    else {
                        step.setNext(returnBlock);
                        returnBlock.setPrev(step);
                        step = null;
                    }

                }
                else {
                    if (step.getPrev() != null) {
                        step.getPrev().setNext(returnBlock);
                        returnBlock.setPrev(step.getPrev());
                    }
                    else {
                        freeList[returnIndex] = returnBlock;
                    }

                    step.setPrev(returnBlock);
                    returnBlock.setNext(step);
                    step = null;
                }
            }
            while (step != null);
        }
        checkBuddies(returnBlock);

    }


    /**
     * Return the length of the record associated with the handle
     * 
     * @param handle
     *            Memory Handle
     * @return Length of memory
     */
    public int length(MemHandle handle) {

        return handle.getLength();
    }


    /**
     * Finds and creates space in the memory pool
     * 
     * @param byteSize
     *            The size of the bytes to be stored
     * @return
     *         An available freeBlock in the memory pool
     */
    private FreeBlock allocateBlock(int byteSize) {
        FreeBlock freeBlock = null;
        int requiredBlockSize = getTrueBlockSize(byteSize);

        for (int i = 0; i < freeList.length; i++) {
            if (freeList[i] != null) {
                if (freeList[i].getSize() >= byteSize) {
                    freeBlock = freeList[i];
                    break;
                }
            }
        }

        while (freeBlock.getSize() > requiredBlockSize) {
            freeBlock = splitBlock(freeBlock);
        }

        return freeBlock;
    }


    /**
     * Splits a freeBlock in half
     * 
     * @param block
     *            The available freeBlock
     * @return the First half of the freeBlock
     */
    private FreeBlock splitBlock(FreeBlock block) {
        FreeBlock split1 = new FreeBlock(block.getPosition(), block.getSize()
            / 2);
        FreeBlock split2 = new FreeBlock(block.getPosition() + (block.getSize()
            / 2), block.getSize() / 2);
        split1.setPrev(null);
        split1.setNext(split2);
        split2.setPrev(split1);
        for (int i = 0; i < freeList.length; i++) {
            if (freeList[i] == block) {
                freeList[i] = block.getNext();
                if (freeList[i] != null) {
                    freeList[i].setPrev(null);
                }
                freeList[(int)(Math.log(split1.getSize()) / Math.log(2)) - 1] =
                    split1;
            }
        }
        return split1;
    }


    /**
     * Merges all freeBlock buddies in the freeList
     */
    private void checkBuddies(FreeBlock block) {
        int blockIndex = (int)((Math.log(block.getSize()) / Math.log(2)) - 1);

        // block is the only block in the LList

        // Safety net to make sure it is linked
        /*
         * if ((block.getNext() == null) && (block.getPrev() == null)) {
         * return;
         * }
         */
        FreeBlock mergedBlock = null;
        // checking if next block is a buddy
        if (block.getNext() != null) {

            FreeBlock next = block.getNext();

            if ((block.getPosition() | block.getSize()) == (next.getPosition()
                | next.getSize())) {

                mergedBlock = new FreeBlock(block.getPosition(), block.getSize()
                    * 2);

                // If the block is the first in the list, set the first to be
                // the following block
                if (block.getPrev() == null) {
                    freeList[blockIndex] = next.getNext();
                    if (freeList[blockIndex] != null) {
                        freeList[blockIndex].setPrev(null);
                    }
                }
                else { // Fixing the linkage
                    FreeBlock prev = block.getPrev();
                    prev.setNext(next.getNext());
                    if (next.getNext() != null) {
                        next.getNext().setPrev(prev);
                    }

                    next.setNext(null);
                    next.setPrev(null);
                    block.setNext(null);
                    block.setPrev(null);
                }

            }
        }

        if (block.getPrev() != null) {
            FreeBlock prev = block.getPrev();
            if ((block.getPosition() | block.getSize()) == (prev.getPosition()
                | prev.getSize())) {

                mergedBlock = new FreeBlock(prev.getPosition(), block.getSize()
                    * 2);

                // If these were the only two blocks in the LList
                if (block.getNext() == null) {
                    freeList[blockIndex] = null;
                }
                else {
                    if (prev.getPrev() == null) {
                        freeList[blockIndex] = block.getNext();
                        freeList[blockIndex].setPrev(null);
                    }
                    else {
                        FreeBlock next = block.getNext();

                        prev.getPrev().setNext(next);
                        next.setPrev(prev.getPrev());

                        prev.setNext(null);
                        prev.setPrev(null);
                        block.setNext(null);
                        block.setPrev(null);
                    }
                }

            }
        }

        // Adding the new merged block back into the list
        if (mergedBlock != null) {

            if (freeList[blockIndex + 1] == null) {
                freeList[blockIndex + 1] = mergedBlock;
                return;
            }

            // Iterate through the list
            FreeBlock step = freeList[blockIndex + 1];

            do {
                if (mergedBlock.getPosition() < step.getPosition()) {
                    mergedBlock.setNext(step);

                    if (step.getPrev() == null) {
                        freeList[blockIndex + 1] = mergedBlock;
                        step.setPrev(mergedBlock);
                        step = null;
                    }
                    else {

                        step.getPrev().setNext(mergedBlock);
                        mergedBlock.setPrev(step.getPrev());
                        step = null;
                    }
                }
                else {
                    if (step.getNext() == null) {
                        step.setNext(mergedBlock);
                        mergedBlock.setPrev(step);
                        step = null;
                    }
                    if (step != null) {
                        step = step.getNext();
                    }
                }
            }
            while (step != null);

            freeList[blockIndex + 1].setPrev(null);
            checkBuddies(mergedBlock);
        }
    }


    /**
     * Checks and adjusts the size of the pool before inserting
     * 
     * @param byteSize
     */
    private void checkSize(int byteSize) {
        while (byteSize > this.size) {
            resize();
        }

        boolean isFull = true;
        int trueBlockSize = getTrueBlockSize(byteSize);

        for (int i = 0; i < freeList.length; i++) {

            if (freeList[i] != null) {
                if (freeList[i].getSize() >= byteSize) {
                    isFull = false;
                }
            }

        }
        if (isFull) {
            resize();
        }
    }


    /**
     * Double the size of the memPool
     */
    private void resize() {

        size *= 2;

        byte[] newMemPool = new byte[size];
        System.arraycopy(memPool, 0, newMemPool, 0, size / 2);
        memPool = newMemPool;

        FreeBlock[] newFreeList = new FreeBlock[freeList.length + 1];
        System.arraycopy(freeList, 0, newFreeList, 0, freeList.length);

        FreeBlock newBlock = new FreeBlock(size / 2, size / 2);
        int newIndex = newFreeList.length - 2;
        if (newFreeList[newIndex] != null) {
            newFreeList[newFreeList.length - 2].setNext(newBlock);
            newBlock.setPrev(newFreeList[newFreeList.length - 2]);
        }
        else {
            newFreeList[newIndex] = newBlock;
        }

        freeList = newFreeList;

        System.out.printf("Memory pool expanded to %d bytes%n", size);

        checkBuddies(newBlock);
    }


    /**
     * Finds the needed block size (int) for an amount of bytes
     * 
     * @param byteSize
     * @return int
     */
    private int getTrueBlockSize(int byteSize) {
        int trueBlockSize = 1;
        while (trueBlockSize < byteSize) {
            trueBlockSize *= 2;
        }
        return trueBlockSize;
    }

}
