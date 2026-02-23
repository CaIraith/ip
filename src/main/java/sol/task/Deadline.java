package sol.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * <p>
 * Extends the Task class and stores a due date.
 * Provides methods to format the task for display and file storage.
 */
public class Deadline extends Task {
    private final LocalDate by;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a Deadline task with a description and a due date string.
     *
     * @param description Description of the task
     * @param byString Due date in yyyy-MM-dd format
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = LocalDate.parse(byString, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMAT);
    }

    /**
     * Creates a Deadline object from an array of strings (parsed from file).
     * <p>
     * Automatically marks the task as done if indicated.
     *
     * @param parts Array of strings representing task data
     * @return A Deadline object
     */
    public static Deadline fromFileString(String[] parts) {
        Deadline d = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            d.markDone();
        }
        return d;
    }
}
