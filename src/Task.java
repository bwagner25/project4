import java.util.Iterator;

public class Task implements Comparable<Task>,Iterable<Task> {
    private String title;
    private String description;
    private String priority;

    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "(" + title + "," + priority + ")";
    }

    @Override
    public int compareTo(Task other) {
        if(this.priority.equals(other.priority)){
            return this.title.compareTo(other.title);
        }else{
            return this.priority.compareTo(other.priority);
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }
}
