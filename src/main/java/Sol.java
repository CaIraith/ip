import java.util.Scanner;

public class Sol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo =
                  " / ___|  ___ | |\n"
                + " \\___ \\ / _ \\| |\n"
                + "  ___) | (_) | |\n"
                + " |____/ \\___/|_|\n";

        System.out.println("Hello. I am\n" + logo);
        System.out.println("What would you like to do?");
        String input = "";
        int index = 0;
        String[] tasks = new String[100];
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            }
            else {
                System.out.println("Added: " + input);
                tasks[index] = input;
                index++;
            }
        }
        System.out.println("Goodbye. See you next time.");
        scanner.close();
    }
}
