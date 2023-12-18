package org.ulearn.analytics.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.ulearn.analytics.Task;
import org.ulearn.analytics.TaskType;

@DatabaseTable(tableName = "taskResult")
public class TaskResultEntity {
    public static final String TASK_NAME_COLUMN = "taskName";
    public static final String TASK_TYPE_COLUMN = "taskType";
    public static final String MAX_POINTS_COLUMN = "maxPoints";
    public static final String TOPIC_ID_COLUMN = "topicID";

    @DatabaseField(generatedId = true)
    private long taskResultID;

    @DatabaseField()
    private int currentPoints;

    @DatabaseField()
    private long taskID;

    @DatabaseField()
    private long studentID;

    public TaskResultEntity(){}

    public TaskResultEntity(int currentPoints, long taskID, long studentID){
        this.currentPoints = currentPoints;
        this.taskID = taskID;
        this.studentID = studentID;
    }

    @Override
    public String toString(){
        return String.format("TaskResultEntity{%ncurrentPoints=%s%ntaskID=%s%nstudentID=%s%n}%n",
                currentPoints, taskID, studentID);
    }
}
