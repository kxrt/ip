package duke.task;

/**
 * Encapsulates a task stored in Apollo.
 *
 * @author Kartikeya
 */
public abstract class DukeTask {
    // Description of task
    private final String description;

    // Indicates if the task has been completed
    private boolean isDone;

    /**
     * Constructor for a DukeTask object.
     *
     * @param description description of the task
     */
    public DukeTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task completion.
     *
     * @return string signifying status of task completion
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as complete and returns a string signifying this.
     *
     * @return string signifying task completion
     */
    public String markAsDone() {
        isDone = true;
        return "Good job! This task has been completed:\n  " + this;
    }

    /**
     * Marks a task as not complete and returns a string signifying this.
     *
     * @return string signifying incomplete task
     */
    public String markAsNotDone() {
        isDone = false;
        return "Whoops! This task is now yet to be completed:\n  " + this;
    }

    /**
     * Checks if a task is marked as completed.
     *
     * @return true if task is marked as complete; false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts task representation to a string suitable for saving in storage
     *
     * @return string representation of task suitable for storage
     */
    public abstract String getStorageString();

    /**
     * Checks if the task's description contains input string.
     *
     * @param matcher input string
     * @return true if the matcher exists in the task's description; false otherwise
     */
    public boolean checkDescriptionMatch(String matcher) {
        return description.contains(matcher);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
