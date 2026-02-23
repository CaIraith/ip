package sol.core;

import java.util.ArrayList;
import java.util.List;

import sol.storage.Storage;
import sol.task.Task;

/**
 * Represents a list of tasks and handles operations on them.
 * <p>
 * Responsible for adding, deleting, marking/unmarking tasks,
 * and persisting changes via the Storage class.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with a given storage.
     * Loads tasks from storage.
     *
     * @param storage Storage object used to save and load tasks
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list and saves the updated list to storage.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Deletes a task at the specified index and saves the updated list.
     *
     * @param index Index of the task to delete
     * @return The Task object that was removed
     */
    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        storage.saveTasks(tasks);
        return removed;
    }

    /**
     * Marks the task at the specified index as done and saves the list.
     *
     * @param index Index of the task to mark
     * @return The Task object that was marked
     */
    public Task markTask(int index) {
        tasks.get(index).markDone();
        storage.saveTasks(tasks);
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as not done and saves the list.
     *
     * @param index Index of the task to unmark
     * @return The Task object that was unmarked
     */
    public Task unmarkTask(int index) {
        tasks.get(index).markUndone();
        storage.saveTasks(tasks);
        return tasks.get(index);
    }

    /**
     * Finds all tasks that contain the given keyword in their description.
     *
     * @param keyword Keyword to search for
     * @return List of tasks containing the keyword
     */
    public List<Task> findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public int size() {
        return tasks.size();
    }
}
