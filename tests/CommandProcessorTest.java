import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-09-01
 * 
 *        Command Processor Class Tests
 * 
 */
public class CommandProcessorTest extends TestCase {

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


    /**
     * Testing found record
     */
    public void testSearch2() {
        cp.readCommands("testFiles/testSearch2.txt");
        assertEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview of"
            + " HCI research at VT\r\n"
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
     * Testing the insert command
     */
    public void testInsert() {
        cp.readCommands("testFiles/testInsert.txt");

    }
}
