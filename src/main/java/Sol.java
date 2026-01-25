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
                String[] parts = input.split(" ", 2);
                String type = parts[0];
                String description = parts.length > 1 ? parts[1] : "";
                Task task;

                if (type.equals("todo")) {
                    task = new ToDo(description);
                } else if (type.equals("deadline")) {
                    String[] deadlineParts = description.split(" /by ", 2);
                    task = new Deadline(deadlineParts[0], deadlineParts[1]);
                } else if (type.equals("event")) {
                    String[] eventParts1 = description.split(" /from ", 2);
                    String desc = eventParts1[0];
                    String[] eventParts2 = eventParts1[1].split(" /to ", 2);
                    String start = eventParts2[0];
                    String end = eventParts2[1];
                    task = new Event(desc, start, end);
                } else {
                    task = new Task(input);
                }
                tasks[index] = task;
                System.out.println("Added: " + tasks[index].toString());
                System.out.println("You have " + index + " tasks in this list.");
                index++;
            }
        }
        System.out.println("Goodbye. See you next time.");
        scanner.close();
    }
}
