package sol;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sol.core.Sol;

/**
 * JUnit tests for the Sol application components.
 * <p>
 * Tests include parsing commands and verifying correct construction
 * of Event and Deadline tasks.
 */
public class SolTest {

    /**
     * Tests that a deadline task added via Sol#getResponse(String)
     * is correctly persisted to storage and reloaded when a new
     * Sol instance is created.
     *
     * <p>This test verifies integration between command parsing,
     * task creation, storage saving, and storage loading.
     * It simulates an application restart by creating a second
     * Sol instance using the same file path.</p>
     *
     * Expected behavior:
     * - The added deadline task is written to file.
     * - A new Sol instance loads the task from file.
     * - The task appears when the "list" command is executed.
     */
    @Test
    void addDeadline_taskIsPersistedAndReloaded_correctlyLoaded() {
        Sol sol = new Sol("data/test_tasks.txt");

        sol.getResponse("deadline Submit report /by 2026-03-01");

        // Simulate restart
        Sol solReloaded = new Sol("data/test_tasks.txt");
        String response = solReloaded.getResponse("list");

        assertTrue(response.contains("Submit report"));
    }

    /**
     * Tests that adding a deadline without the required "/by"
     * keyword returns an appropriate error message.
     *
     * <p>This test verifies that command validation logic
     * correctly detects malformed deadline commands and
     * prevents invalid tasks from being added.</p>
     * <p>
     * Expected behavior:
     * - The command is rejected.
     * - An error message indicating the correct usage is returned.
     */
    @Test
    void addDeadline_missingByKeyword_returnsErrorMessage() {
        Sol sol = new Sol("data/test.txt");

        String response = sol.getResponse("deadline Submit report 2026-03-01");

        assertTrue(response.contains("Deadlines must include /by"));
    }
}
