package sol.parser;

import sol.core.SolException;

/**
 * Utility class for parsing user input commands in the Sol application.
 * <p>
 * Provides methods to extract command types, arguments, and validate number-based commands.
 */
public class Parser {
    /**
     * A string containing all valid commands for display in error messages.
     */
    public static final String VALID_COMMANDS =
            """
                    Valid commands:
                      list
                      mark <task number>
                      unmark <task number>
                      delete <task number>
                      find <keyword>
                      todo <description>
                      deadline <description> /by <yyyy-MM-dd>
                      event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>
                      timed <description> /duration <hours>
                      bye""";

    /**
     * Returns the command type (first word) from the input string.
     *
     * @param input The user input string
     * @return The command type (e.g., "todo", "mark")
     */
    public static String getCommandType(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0];
    }

    /**
     * Returns the arguments portion (everything after the command type) from the input string.
     *
     * @param input The user input string
     * @return The arguments string, or empty string if none
     */
    public static String getArguments(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /**
     * Validates that a number-based command (like "mark 2") has the correct format.
     *
     * @param input The user input string
     * @param type The command type (e.g., "mark", "unmark", "delete")
     * @throws SolException if the command does not match the expected format
     */
    public static void validateNumberCommand(String input, String type) throws SolException {
        if (!input.matches(type + " \\d+")) {
            throw new SolException("Invalid command format. Correct usage: "
                    + type + " <task number>");
        }
    }
}
