package sol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
