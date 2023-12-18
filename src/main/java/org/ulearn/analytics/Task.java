package org.ulearn.analytics;

public class Task {
    private TaskType taskType;
    private int maxPoints;
    private String taskName;
    private Topic topic;

    public Task(TaskType taskType, int maxPoints, Topic topic){
        this.taskType = taskType;
        this.maxPoints = maxPoints;
        this.topic = topic;
    }

    public Task(TaskType taskType, int maxPoints, Topic topic, String taskName){
        this.taskType = taskType;
        this.maxPoints = maxPoints;
        this.topic = topic;
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

    public Topic getTopic(){
        return topic;
    }

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s", taskName, taskType, maxPoints, topic.getTopicName());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Task)) {
            return false;
        }
        Task task = (Task) object;

        return task.taskType == taskType
                && task.maxPoints == maxPoints
                && task.taskName.equals(taskName)
                && task.topic.equals(topic);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + taskType.ordinal();
        result = 31 * result + maxPoints;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + topic.hashCode();
        return result;
    }
}
