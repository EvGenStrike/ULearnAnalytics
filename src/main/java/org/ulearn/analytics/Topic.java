package org.ulearn.analytics;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;

public class Topic {
    private String topicName;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Topic(String topicName){
        this.topicName = topicName;
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
}