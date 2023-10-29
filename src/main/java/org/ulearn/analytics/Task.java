package org.ulearn.analytics;

public abstract class Task {
    private TaskType taskType;
    private int maxPoints;
    private String taskName;

    public Task(TaskType taskType, int maxPoints){
        this.taskType = taskType;
        this.maxPoints = maxPoints;
    }

    public Task(TaskType taskType, int maxPoints, String taskName){
        this.taskType = taskType;
        this.maxPoints = maxPoints;
        this.taskName = taskName;
    }

    public TaskType getTaskType(){
        return taskType;
    }

    public int getMaxPoints(){
        return maxPoints;
    }

    public String getTaskName(){
        return taskName;
    }
}
