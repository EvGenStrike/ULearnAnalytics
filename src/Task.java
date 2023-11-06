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

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s", taskName, taskType, maxPoints, topic.getTopicName());
    }
}
