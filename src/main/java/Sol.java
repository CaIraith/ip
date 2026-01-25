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
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("You said: " + input);
            }
        }
        System.out.println("Goodbye. See you next time.");
        scanner.close();
    }
}
