package sol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;

public class SolTest {
    @Test
    public void addTodoTest() {
        TaskList tasks = new TaskList(new Storage());
        ToDo todo = new ToDo("Do homework");
        tasks.addTask(todo);

        assertEquals("Do homework", tasks.getTasks().get(0).getDescription());
        new File("data/test.txt");
    }

    @Test
    public void parseCommandTest() throws SolException {
        String input = "mark 1";
        assertEquals("mark", Parser.getCommandType(input));
        assertEquals("1", Parser.getArguments(input));
    }
}
