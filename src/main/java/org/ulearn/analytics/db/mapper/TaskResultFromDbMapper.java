package org.ulearn.analytics.db.mapper;

import org.ulearn.analytics.db.model.StudentEntity;
import org.ulearn.analytics.db.model.TaskResultEntity;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.models.Task;
import org.ulearn.analytics.models.TaskResult;

public class TaskResultFromDbMapper {
    public static TaskResult map(TaskResultEntity taskResultEntity, Task task, Student student){
        return new TaskResult(task, taskResultEntity.getCurrentPoints(), student);
    }
}
