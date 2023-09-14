import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Xavier Akers
 * 
 * @version 1.0
 * 
 * @since 2023-08-24
 * 
 *        A simple file parser
 * 
 */
public class CommandProcessor {
    private SeminarDB controller;

    /**
     * Default constructor to initialize controller
     */
    public CommandProcessor() {
        this.controller = new SeminarDB(512, 4);
    }


    /**
     * Constructor to receive a controller
     * 
     * @param controller
     *            Controls the HashTable and MemoryManager
     */
    public CommandProcessor(SeminarDB controller) {
        this.controller = controller;
    }


    /**
     * 1. Read commands
     * 2. Read following arguments
     * 3. Send arguments to respective function based on command
     * 
     * @param filename
     *            A string containing the input file
     */
    public void readCommands(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));

            String[] line;
            String cmd;
            String id = null;
            while (sc.hasNextLine()) {

                line = sc.nextLine().trim().split("\\s+");
                cmd = line[0];
                if (line.length == 2) {
                    id = line[1];
                }

                // Skips the random newlines in the input file
                // if (!line.isEmpty() || !line.equals("%n")) {
                // !cmd.equals("%n") &&
                if (!cmd.isEmpty()) {
                    switch (cmd) {
                        case "insert":
                            // Array to store input data to pass to
                            // SeminarDB
                            ArrayList<String> data = new ArrayList<>();

                            String title = sc.nextLine();
                            String[] nums = sc.nextLine().trim().split("\\s+");
                            String date = nums[0];
                            String length = nums[1];
                            String x = nums[2];
                            String y = nums[3];
                            String cost = nums[4];
                            String[] keywords = sc.nextLine().trim().split(
                                "\\s+");
                            String desc = sc.nextLine().trim();

                            data.add(id);
                            data.add(title);
                            data.add(date);
                            data.add(length);
                            data.add(x);
                            data.add(y);
                            data.add(cost);
                            data.add(desc);

                            // Adding keywords at the end
                            for (int i = 0; i < keywords.length; i++) {
                                data.add(keywords[i]);
                            }

                            int byteSize = controller.insertSem(data);
                            if (byteSize != 0) {
                                System.out.printf(
                                    "Successfully inserted record with ID %s%n",
                                    id);
                                System.out.println(controller.searchSem(id));
                                System.out.printf("Size: %d%n", byteSize);
                            }
                            else {
                                System.out.printf(
                                    "Insert FAILED - There is already"
                                        + " a record with ID %s%n", id);
                            }

                            break;
                        case "delete":
                            controller.deleteSem(id);
                            break;
                        case "search":
                            Seminar search = controller.searchSem(id);
                            if (search != null) {
                                System.out.printf("Found record with ID %s:%n",
                                    id);
                                System.out.println(search);
                            }
                            else {
                                System.out.printf("Search FAILED -- There"
                                    + " is no record with ID %s%n", id);
                            }
                            break;
                        case "print":
                            // String type = sc.next();
                            switch (id) {
                                case "blocks":
                                    controller.printBlocks();
                                    break;
                                case "hashtable":
                                    controller.printHashTable();
                                    break;
                            }
                            break;
                        default:

                            System.out.println("Invalid command");
                    }
                }
            }
            sc.close();

        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
