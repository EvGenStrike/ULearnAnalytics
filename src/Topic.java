import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Topic {
    private String topicName;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Topic(String topicName){
        this.topicName = topicName;
    }

    public String getTopicName(){
        return topicName;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public void removeTask(String taskName) throws InstanceNotFoundException {
        for (var task : tasks){
            if (task.getTaskName().equals(taskName)){
                tasks.remove(task);
            }
        }
        throw new InstanceNotFoundException();
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("%s:%n", topicName));
        for (var task : tasks){
            sb.append(String.format("%s%n", task.toString()));
        }
        return sb.toString();
    }
}