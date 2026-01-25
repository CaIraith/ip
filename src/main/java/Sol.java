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
        int index = 1;
        Task[] tasks = new Task[101];

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println(i + ". " + tasks[i].toString());
                }
            } else if (input.matches("mark \\d+")) {
                String[] parts = input.split(" ");
                int idx = Integer.parseInt(parts[1]);
                tasks[idx].markDone();
                System.out.println("Marked the below task as completed.");
                System.out.println("   " + tasks[idx].toString());
            } else if (input.matches("unmark \\d+")) {
                String[] parts = input.split(" ");
                int idx = Integer.parseInt(parts[1]);
                tasks[idx].markUndone();
                System.out.println("Marked the below task as incomplete.");
                System.out.println("   " + tasks[idx].toString());
            }
            else {
                System.out.println("Added: " + input);
                tasks[index] = new Task(input);
                index++;
            }
        }
        System.out.println("Goodbye. See you next time.");
        scanner.close();
    }
}
