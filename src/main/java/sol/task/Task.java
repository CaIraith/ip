package sol.task;

/**
 * Represents a generic task with a description and completion status.
 * <p>
 * This is the base class for all task types (ToDo, Deadline, Event).
 * Provides methods to get description, mark as done/undone, and
 * convert to/from file storage format.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        assert description != null : "Task description cannot be null";
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Creates a Task object from a string line read from file.
     * <p>
     * Determines the task type (T/D/E) and reconstructs the correct subclass.
     *
     * @param line String from file representing a task
     * @return Task object (ToDo, Deadline, or Event)
     * @throws IllegalArgumentException if the task type is unknown
     */
    public static Task fromFileString(String line) {
        assert line != null && !line.isBlank() : "Input line must not be null or empty";
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Line must have at least 3 parts: type, done, description";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        Task task;

        switch (type) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            assert parts.length == 4 : "Deadline must have description and date";
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            assert parts.length == 5 : "Event must have description, start, and end date";
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        case "F":
            task = FixedDuration.fromFileString(parts);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type");
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
