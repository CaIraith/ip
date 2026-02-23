package sol.storage;

import sol.task.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles reading from and writing to a file to persist tasks.
 * <p>
 * Provides methods to load tasks from the file and save the current task list.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath Path to the file used for saving and loading tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by filePath.
     * <p>
     * If the file does not exist, returns an empty task list.
     * Handles any IOExceptions internally and prints an error message.
     *
     * @return ArrayList of Task objects loaded from the file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }

    /**
     * Saves the provided task list to the file specified by filePath.
     * <p>
     * Creates the parent directories if they do not exist.
     * Handles any IOExceptions internally and prints an error message.
     *
     * @param tasks ArrayList of Task objects to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            new File(filePath).getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : tasks) {
                    writer.write(task.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
