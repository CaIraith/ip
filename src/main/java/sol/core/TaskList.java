package sol.core;

import java.util.ArrayList;

import sol.storage.Storage;
import sol.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        storage.saveTasks(tasks);
        return removed;
    }

    public Task markTask(int index) {
        tasks.get(index).markDone();
        storage.saveTasks(tasks);
        return tasks.get(index);
    }

    public Task unmarkTask(int index) {
        tasks.get(index).markUndone();
        storage.saveTasks(tasks);
        return tasks.get(index);
    }

    /**
     * Finds all tasks that contain the given keyword in their description.
     *
     * @param keyword Keyword to search for
     * @return ArrayList of tasks containing the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }

    public int size() {
        return tasks.size();
    }
}
