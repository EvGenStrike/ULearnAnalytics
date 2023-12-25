package org.ulearn.analytics.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "taskResult")
public class TaskResultEntity {

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

    public long getTaskID(){
        return taskID;
    }

    public int getCurrentPoints(){
        return currentPoints;
    }

    public long getStudentID(){
        return studentID;
    }

    @Override
    public String toString(){
        return String.format("TaskResultEntity{%ncurrentPoints=%s%ntaskID=%s%nstudentID=%s%n}%n",
                currentPoints, taskID, studentID);
    }
}
