package org.ulearn.analytics.models;

public class TaskResult {
    private Task task;
    private int currentPoints;
    private Student student;

    public TaskResult(Task task, int currentPoint, Student student){
        this.task = task;
        this.currentPoints = currentPoint;
        this.student = student;
    }

    public Task getTask(){
        return task;
    }

    public int getCurrentPoints(){
        return currentPoints;
    }

    public Student getStudent(){
        return student;
    }

    @Override
    public String toString(){
        return String.format("%s:%d", task.toString(), currentPoints);
    }
}
