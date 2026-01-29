import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

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

    public static Event fromFileString(String[] parts) {
        Event e = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            e.markDone();
        }
        return e;
    }
}
