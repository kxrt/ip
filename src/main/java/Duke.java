import java.util.Scanner;

/**
 * Apollo is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private Scanner s;
    private final String divider =
        "\n-----------------------------------------------";

    // Stores all the items given to the chatbot
    private Storage storage;
    private TaskList itemList;

    public Duke() {
        try {
            s = new Scanner(System.in);
            storage = new Storage();
            itemList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialises the chatbot.
     */
    public void start() {
        String intro = "Welcome to Apollo!\n" +
            "How can I help you today?";
        System.out.println(intro + divider);
        run();
    }

    /**
     * Runs Apollo. Waits for input lines and
     * processes them accordingly.
     */
    private void run() {
        try {
            checkInput(s.nextLine());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + divider);
            run();
        }
    }

    /**
     * Processes chat inputs using a switch statement, throwing a DukeException
     * on incorrect inputs.
     * @param inputString String given to Apollo
     * @throws DukeException Indicates incorrect inputs
     */
    private void checkInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        String output = "";
        try {
            switch (input[0]) {
                case "bye": {
                    itemList.save(storage);
                    System.out.println("Goodbye, see you soon!" + divider);
                    return;
                }
                case "list": {
                    output = itemList.toString();
                    break;
                }
                case "mark": {
                    output = itemList.mark(Integer.parseInt(input[1]));
                    break;
                }
                case "unmark": {
                    output = itemList.unmark(Integer.parseInt(input[1]));
                    break;
                }
                case "delete": {
                    output = itemList.deleteItem(Integer.parseInt(input[1]));
                    break;
                }
                default: {
                    output = itemList.addItem(input);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing command description.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please recheck your number input, " +
                "including trailing spaces.");
        }
        System.out.println(output + divider);
        run();
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.start();
    }
}
