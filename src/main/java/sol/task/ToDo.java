package sol.task;

/**
 * Represents a simple ToDo task without any date constraints.
 * <p>
 * Extends the Task class and provides methods for display and file storage.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
