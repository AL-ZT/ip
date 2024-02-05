package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidTaskException;

public class TaskListTest {
    @Test
    void deadline_wrongDateFormat() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(null, "deadline", new String[]{"testing title", "Oct 09 2024"});
            fail();
        } catch (InvalidTaskException te) {
            assertEquals(te.getMessage(), "Date not in format: yyyy-MM-dd, please try again.");
        }
    }

    @Test
    void input_taskId_outOfBounds() {
        try {
            TaskList tasks = new TaskList();
            tasks.mark(null, new String[]{"-1"});
        } catch (IllegalArgumentException ie) {
            assertEquals(ie.getMessage(), "Index number cannot be out of bounds.");
        }
    }
}
