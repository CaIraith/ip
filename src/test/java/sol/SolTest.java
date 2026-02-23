package sol;

import org.junit.jupiter.api.Test;
import sol.core.SolException;
import sol.parser.Parser;
import sol.task.Deadline;
import sol.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolTest {
    @Test
    public void parseCommandTest() throws SolException {
        String input = "mark 1";
        assertEquals("mark", Parser.getCommandType(input));
        assertEquals("1", Parser.getArguments(input));
    }

    @Test
    public void eventConstructorTest() {
        Event e = new Event("Camp",
                "2026-03-01",
                "2026-03-05");

        String expected = "[E][ ] Camp (from: Mar 01 2026 to: Mar 05 2026)";

        assertEquals(expected, e.toString());
    }

    @Test
    public void deadlineConstructorTest() {
        Deadline d = new Deadline("Submit report", "2026-03-01");

        String expected = "[D][ ] Submit report (by: Mar 01 2026)";

        assertEquals(expected, d.toString());
    }
}
