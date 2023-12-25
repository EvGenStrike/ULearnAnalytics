package org.ulearn.analytics.db.mapper;

import org.ulearn.analytics.db.model.TaskEntity;
import org.ulearn.analytics.db.model.TopicEntity;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.db.model.StudentEntity;
import org.ulearn.analytics.models.Task;
import org.ulearn.analytics.models.Topic;

public class TaskFromDbMapper {
    public static Task map(TaskEntity taskEntity, Topic topic){
        return new Task(taskEntity.getTaskType(), taskEntity.getMaxPoints(), topic, taskEntity.getTaskName());
    }
}
