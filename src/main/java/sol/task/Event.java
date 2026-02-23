package sol.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date, i.e., an Event.
 * <p>
 * Extends the Task class and stores a start and end date.
 * Provides methods to format the task for display and file storage.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs an Event task with a description, start date, and end date.
     *
     * @param description Description of the event
     * @param startString Start date in yyyy-MM-dd format
     * @param endString End date in yyyy-MM-dd format
     */
    public Event(String description, String startString, String endString) {
        super(description);
        this.start = LocalDate.parse(startString, INPUT_FORMAT);
        this.end = LocalDate.parse(endString, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(OUTPUT_FORMAT)
                + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + start.format(INPUT_FORMAT)
                + " | " + end.format(INPUT_FORMAT);
    }

    /**
     * Creates an Event object from an array of strings (parsed from file).
     * <p>
     * Automatically marks the task as done if indicated.
     *
     * @param parts Array of strings representing event data
     * @return An Event object
     */
    public static Event fromFileString(String[] parts) {
        Event e = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            e.markDone();
        }
        return e;
    }
}
