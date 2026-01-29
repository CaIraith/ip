import java.util.Scanner;
import java.util.ArrayList;

public class Sol {
    private static final String VALID_COMMANDS =
        "Valid commands:\n"
      + "  list\n"
      + "  mark <task number>\n"
      + "  unmark <task number>\n"
      + "  delete <task number>\n"
      + "  todo <description>\n"
      + "  deadline <description> /by <time>\n"
      + "  event <description> /from <start> /to <end>\n"
      + "  bye";

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
        ArrayList<Task> tasks = Storage.loadTasks();

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i+1) + ". " + tasks.get(i));
                    }
                } else if (input.matches("mark \\d+")) {
                    String[] parts = input.split(" ");
                    int idx = Integer.parseInt(parts[1]) - 1;
                    tasks.get(idx).markDone();
                    Storage.saveTasks(tasks);
                    System.out.println("Marked the below task as completed.");
                    System.out.println("   " + tasks.get(idx));
                } else if (input.matches("unmark \\d+")) {
                    String[] parts = input.split(" ");
                    int idx = Integer.parseInt(parts[1]) - 1;
                    tasks.get(idx).markUndone();
                    Storage.saveTasks(tasks);
                    System.out.println("Marked the below task as incomplete.");
                    System.out.println("   " + tasks.get(idx));
                } else if (input.matches("delete \\d+")) {
                    String[] parts = input.split(" ");
                    int idx = Integer.parseInt(parts[1]) - 1;
                    System.out.println("Removed the below task from the list.");
                    System.out.println("   " + tasks.get(idx));
                    tasks.remove(idx);
                    Storage.saveTasks(tasks);
                }
                else {
                    String[] parts = input.split(" ", 2);
                    String type = parts[0];
                    String description = parts.length > 1 ? parts[1] : "";
                    Task task;

                    if (type.equals("todo")) {
                        if (description.isEmpty()) {
                            throw new SolException("Error. The description of a ToDo cannot be empty.\n"
                            + "Try again with command format: todo <description>");
                        }
                        task = new ToDo(description);
                    } else if (type.equals("deadline")) {
                        if (description.isEmpty() || !description.contains(" /by ")) {
                            throw new SolException("Error. A deadline must include a description and /by time.\n"
                            + "Try again with command format: deadline <description> /by <time>");
                        }
                        String[] deadlineParts = description.split(" /by ", 2);
                        task = new Deadline(deadlineParts[0], deadlineParts[1]);
                    } else if (type.equals("event")) {
                        if (description.isEmpty() || !description.contains(" /from ") || !description.contains(" /to ")) {
                            throw new SolException("Error. An event must include a description, /from time and /by time.\n"
                            + "Try again with command format: event <description> /from <start> /to <end>");
                        } 
                        String[] eventParts1 = description.split(" /from ", 2);
                        String desc = eventParts1[0];
                        String[] eventParts2 = eventParts1[1].split(" /to ", 2);
                        String start = eventParts2[0];
                        String end = eventParts2[1];
                        task = new Event(desc, start, end);
                    } else {
                        throw new SolException("Invalid command.\n" + VALID_COMMANDS);
                    }
                    tasks.add(task);
                    Storage.saveTasks(tasks);
                    System.out.println("Added: " + task);
                    System.out.println("You have " + tasks.size() + " tasks in this list.");
                }
            } catch (SolException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye. See you next time.");
        scanner.close();
    }
}
