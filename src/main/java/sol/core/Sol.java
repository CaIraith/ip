package sol.core;

import java.util.List;
import java.util.stream.IntStream;

import sol.parser.Parser;
import sol.storage.Storage;
import sol.task.*;

/**
 * Main class for the Sol task management application.
 * Handles user interaction, command parsing, and task management.
 */
public class Sol {
    private final TaskList tasks;

    /**
     * Constructs a Sol application instance.
     */
    public Sol() {
        Storage storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage);
    }

    public Sol(String filepath) {
        Storage storage = new Storage(filepath);
        tasks = new TaskList(storage);
    }

    private static Task getEvent(String args) throws SolException {
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new SolException("Events must include /from <yyyy-MM-dd> /to <yyyy-MM-dd>\nUsage: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
        String[] fromParts = args.split(" /from ", 2);
        String desc = fromParts[0];
        String[] toParts = fromParts[1].split(" /to ", 2);
        return new Event(desc, toParts[0], toParts[1]);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String type = Parser.getCommandType(input);
            String args = Parser.getArguments(input);

            switch (type) {
            case "bye":
                return "Goodbye. See you next time.";

            case "list":
                if (tasks.size() == 0) {
                    return "Your task list is empty.";
                }
                StringBuilder listOutput = new StringBuilder("Here are your tasks:\n");
                IntStream.range(0, tasks.size())
                        .forEach(i -> listOutput.append((i + 1))
                                .append(". ")
                                .append(tasks.getTasks().get(i))
                                .append("\n"));
                return listOutput.toString();

            case "mark":
                Parser.validateNumberCommand(input, "mark");
                int markIdx = Integer.parseInt(args) - 1;
                return "Marked the below task as completed.\n   "
                        + tasks.markTask(markIdx);

            case "unmark":
                Parser.validateNumberCommand(input, "unmark");
                int unmarkIdx = Integer.parseInt(args) - 1;
                return "Marked the below task as incomplete.\n   "
                        + tasks.unmarkTask(unmarkIdx);

            case "delete":
                Parser.validateNumberCommand(input, "delete");
                int delIdx = Integer.parseInt(args) - 1;
                return "Removed the below task from the list.\n   "
                        + tasks.deleteTask(delIdx)
                        + "\nYou now have " + tasks.size() + " tasks.";

            case "todo":
                if (args.isEmpty()) {
                    throw new SolException("The description of a ToDo cannot be empty.\nUsage: todo <description>");
                }
                Task todo = new ToDo(args);
                tasks.addTask(todo);
                return "Added:\n   " + todo
                        + "\nYou now have " + tasks.size() + " tasks.";

            case "deadline":
                if (!args.contains(" /by ")) {
                    throw new SolException("Deadlines must include /by <yyyy-MM-dd>");
                }
                String[] deadlineParts = args.split(" /by ", 2);
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(deadline);
                return "Added:\n   " + deadline
                        + "\nYou now have " + tasks.size() + " tasks.";

            case "event":
                Task event = getEvent(args);
                tasks.addTask(event);
                return "Added:\n   " + event
                        + "\nYou now have " + tasks.size() + " tasks.";

            case "fixed":
                if (!args.contains(" /duration ")) {
                    throw new SolException("Timed tasks must include /duration <hours>");
                }
                String[] timedParts = args.split(" /duration ", 2);
                Task timedTask = new FixedDuration(timedParts[0],
                        Integer.parseInt(timedParts[1]));
                tasks.addTask(timedTask);
                return "Added:\n   " + timedTask
                        + "\nYou now have " + tasks.size() + " tasks.";

            case "find":
                if (args.isEmpty()) {
                    throw new SolException("Please provide a keyword to search for.");
                }
                List<Task> matches = tasks.findTasks(args);
                if (matches.isEmpty()) {
                    return "No matching tasks found for keyword: " + args;
                }
                StringBuilder findOutput = new StringBuilder("Matching tasks:\n");
                for (int i = 0; i < matches.size(); i++) {
                    findOutput.append(i + 1)
                            .append(". ")
                            .append(matches.get(i))
                            .append("\n");
                }
                return findOutput.toString();

            default:
                throw new SolException("Invalid command.\n" + Parser.VALID_COMMANDS);
            }

        } catch (SolException e) {
            return e.getMessage();
        }
    }
}
