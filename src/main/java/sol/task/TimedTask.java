package sol.task;

/**
 * Represents a task that requires a fixed duration to complete
 * but does not have a fixed start or end time.
 * <p>
 * Example: "Read sales report (2 hours)".
 */
public class TimedTask extends Task {
    private final int durationHours; // duration in hours

    /**
     * Constructs a TimedTask.
     *
     * @param description   Description of the task
     * @param durationHours Duration required to complete in hours (must be > 0)
     */
    public TimedTask(String description, int durationHours) {
        super(description);
        if (durationHours <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.durationHours = durationHours;
    }

    public int getDurationHours() {
        return durationHours;
    }

    @Override
    public String toString() {
        // Used ChatGPT to generate String format
        return "[TT]" + super.toString() + " (Duration: " + durationHours + " hours)";
    }

    @Override
    public String toFileString() {
        return "TT | " + (isDone ? "1" : "0") + " | " + description + " | " + durationHours;
    }

    /**
     * Reconstructs a TimedTask from a file string array.
     *
     * @param parts Parts of the line split by " | "
     * @return TimedTask object
     */
    public static TimedTask fromFileString(String[] parts) {
        TimedTask t = new TimedTask(parts[2], Integer.parseInt(parts[3]));
        if (parts[1].equals("1")) {
            t.markDone();
        }
        return t;
    }
}

