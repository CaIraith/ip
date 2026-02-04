package sol;

public class Parser {
    public static final String VALID_COMMANDS =
        "Valid commands:\n"
      + "  list\n"
      + "  mark <task number>\n"
      + "  unmark <task number>\n"
      + "  delete <task number>\n"
      + "  todo <description>\n"
      + "  deadline <description> /by <yyyy-MM-dd>\n"
      + "  event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>\n"
      + "  bye";

    public static String getCommandType(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0];
    }

    public static String getArguments(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public static void validateNumberCommand(String input, String type) throws SolException {
        if (!input.matches(type + " \\d+")) {
            throw new SolException("Invalid command format. Correct usage: " 
                                + type + " <task number>");
        }
    }
}
