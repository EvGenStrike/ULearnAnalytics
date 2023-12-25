package org.ulearn.analytics.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.ulearn.analytics.models.TaskType;

@DatabaseTable(tableName = "task")
public class TaskEntity {
    public static final String TASK_ID_COLUMN = "taskID";
    public static final String TASK_NAME_COLUMN = "taskName";
    public static final String TASK_TYPE_COLUMN = "taskType";
    public static final String MAX_POINTS_COLUMN = "maxPoints";
    public static final String TOPIC_ID_COLUMN = "topicID";

    @DatabaseField(generatedId = true)
    private long taskID;

    @DatabaseField()
    private String taskName;

    @DatabaseField()
    private TaskType taskType;

    @DatabaseField()
    private int maxPoints;

    @DatabaseField(canBeNull = true)
    private long topicID;

    public TaskEntity(){}

    public TaskEntity(String taskName, TaskType taskType, int maxPoints, long topicID){
        this.taskName = taskName;
        this.taskType = taskType;
        this.maxPoints = maxPoints;
        this.topicID = topicID;
    }

    public long getTaskID(){
        return taskID;
    }

    public String getTaskName(){
        return taskName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getMaxPoints(){
        return maxPoints;
    }

    public long getTopicID(){
        return topicID;
    }

    @Override
    public String toString(){
        return String.format("TaskEntity{%ntaskName=%s%ntaskType=%s%nmaxPoints=%s%ntopicID=%s%n}%n",
                taskName, taskType, maxPoints, topicID);
    }
}
