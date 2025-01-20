import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Brownie {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Brownie \nWhat can I do for you?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        while (true) {
            String input = null;
            try {
                input = reader.readLine();
                writer.println(input);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (input.equals("bye")) {
                writer.println("Bye. Hope to see you again soon!");
                writer.flush();
                break;
            }
        }
        writer.close();
    }
}
