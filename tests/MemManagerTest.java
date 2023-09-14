import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-11
 * 
 *        Buddy Method Memory Manager Test
 * 
 */
public class MemManagerTest extends TestCase {

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

    public void testInsert1() {
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

}
