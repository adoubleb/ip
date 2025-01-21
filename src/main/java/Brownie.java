import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Brownie {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Brownie \nWhat can I do for you?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        ArrayList<Task> items = new ArrayList<>();
        while (true) {
            String input = null;
            try {
                input = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (input == null || input.trim().isEmpty()) {
                writer.println("Input cannot be empty. Please try again."); // Informing the user.
                writer.flush();
            } else if (input.equals("bye")) {
                writer.println("Bye. Hope to see you again soon!");
                writer.flush();
                break;
            } else if (input.equals("list")) {
                writer.println("Items in the list:");
                for (int i = 0; i < items.size(); i++) {
                    writer.write(i + 1 + ". " + items.get(i) + "\n");
                }
                writer.flush();
            } else if (input.startsWith("mark")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < 0 || index >= items.size()) {
                    writer.println("Invalid index. Please try again.");
                }
                items.get(index).markAsDone();
                writer.write("Marked " + items.get(index) + " as done.\n");
                writer.flush();
            } else if (input.startsWith("unmark")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < 0 || index >= items.size()) {
                    writer.println("Invalid index. Please try again.");
                }
                items.get(index).markAsUndone();
                writer.write("Noted, " + items.get(index) + " is not done.\n");
                writer.flush();
            } else {
                String inputType = input.split(" ")[0];
                Task toAdd = null;
                String description;
                switch (inputType) {
                    case "todo":
                        description = input.substring(input.indexOf(" ") + 1);
                        toAdd = new Todo(description);
                        break;
                    case "deadline":
                        String delimiter = "by:";
                        int index = input.indexOf(delimiter);
                        if (index == -1) {
                            writer.println("Invalid deadline. Please try again.");
                            writer.flush();
                            continue;
                        }
                        description = input.substring(input.indexOf(" ") + 1, index);
                        String deadline = input.substring(index + delimiter.length());
                        toAdd = new Deadline(description, deadline);
                        break;
                    case "event":
                        String start_delimiter = "start:";
                        String end_delimiter = "end:";
                        int start_index = input.indexOf(start_delimiter);
                        int end_index = input.indexOf(end_delimiter);
                        if (start_index == -1 || end_index == -1) {
                            writer.println("Invalid event. Please try again.");
                            writer.flush();
                            continue;
                        }
                        description = input.substring(input.indexOf(" ") + 1, start_index);
                        String start = input.substring(start_index + start_delimiter.length(),
                                end_index);
                        String end = input.substring(end_index + end_delimiter.length());
                        toAdd = new Event(description, start, end);
                        break;
                    default:
                        writer.println("Invalid input. Please try again.");
                        writer.flush();
                }
                if (toAdd == null) {continue;}
                items.add(toAdd);
                writer.write("Added " + input + "\n");
                writer.flush();
            }
        }
        writer.close();
    }
}
