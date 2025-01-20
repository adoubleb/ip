import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

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
                Task toAdd = new Task(input);
                items.add(toAdd);
                writer.write("Added " + input + "\n");
                writer.flush();
            }
        }
        writer.close();
    }
}
