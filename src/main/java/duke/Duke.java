package duke;

import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Main program of Duke that runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> to start the program.
     *
     * @param filePath File path for persistent task storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs an instance of <code>Duke</code>.
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException de) {
                System.out.println(de);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
