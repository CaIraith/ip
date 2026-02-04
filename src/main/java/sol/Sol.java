package sol;

public class Sol {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Sol(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public void run() {
        String logo =
                  " / ___|  ___ | |\n"
                + " \\___ \\ / _ \\| |\n"
                + "  ___) | (_) | |\n"
                + " |____/ \\___/|_|\n";

        ui.showWelcome(logo);

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();

            try {
                String type = Parser.getCommandType(input);
                String args = Parser.getArguments(input);

                switch (type) {
                    case "bye":
                        isExit = true;
                        break;

                    case "list":
                        for (int i = 0; i < tasks.size(); i++) {
                            ui.showMessage((i+1) + ". " + tasks.getTasks().get(i));
                        }
                        break;

                    case "mark":
                        Parser.validateNumberCommand(input, "mark");
                        int markIdx = Integer.parseInt(args) - 1;
                        ui.showMessage("Marked the below task as completed.\n   " + tasks.markTask(markIdx));
                        break;

                    case "unmark":
                        Parser.validateNumberCommand(input, "unmark");
                        int unmarkIdx = Integer.parseInt(args) - 1;
                        ui.showMessage("Marked the below task as incomplete.\n   " + tasks.unmarkTask(unmarkIdx));
                        break;

                    case "delete":
                        Parser.validateNumberCommand(input, "delete");
                        int delIdx = Integer.parseInt(args) - 1;
                        ui.showMessage("Removed the below task from the list.\n   " + tasks.deleteTask(delIdx));
                        break;

                    case "todo":
                        if (args.isEmpty()) {
                            throw new SolException("The description of a ToDo cannot be empty.\nUsage: todo <description>");
                        }
                        Task todo = new ToDo(args);
                        tasks.addTask(todo);
                        ui.showMessage("Added: " + todo);
                        ui.showMessage("You now have " + tasks.size() + " tasks.");
                        break;

                    case "deadline":
                        if (args.isEmpty() || !args.contains(" /by ")) {
                            throw new SolException("Deadlines must include /by <yyyy-MM-dd>\nUsage: deadline <description> /by <yyyy-MM-dd>");
                        }
                        String[] deadlineParts = args.split(" /by ", 2);
                        Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.addTask(deadline);
                        ui.showMessage("Added: " + deadline);
                        ui.showMessage("You now have " + tasks.size() + " tasks.");
                        break;

                    case "event":
                        if (args.isEmpty() || !args.contains(" /from ") || !args.contains(" /to ")) {
                            throw new SolException("Events must include /from <yyyy-MM-dd> /to <yyyy-MM-dd>\nUsage: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
                        }
                        String[] fromParts = args.split(" /from ", 2);
                        String desc = fromParts[0];
                        String[] toParts = fromParts[1].split(" /to ", 2);
                        Task event = new Event(desc, toParts[0], toParts[1]);
                        tasks.addTask(event);
                        ui.showMessage("Added: " + event);
                        ui.showMessage("You now have " + tasks.size() + " tasks.");
                        break;

                    default:
                        throw new SolException("Invalid command.\n" + Parser.VALID_COMMANDS);
                }

            } catch (SolException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Sol("data/tasks.txt").run();
    }
}
