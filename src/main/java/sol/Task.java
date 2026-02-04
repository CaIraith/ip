package sol;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        Task task;

        switch (type) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
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
