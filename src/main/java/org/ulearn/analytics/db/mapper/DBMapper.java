package org.ulearn.analytics.db.mapper;

import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.db.DB_ORMRepository;
import org.ulearn.analytics.models.Task;
import org.ulearn.analytics.models.TaskResult;
import org.ulearn.analytics.models.Topic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DBMapper {
    private final DB_ORMRepository dbOrm;

    public DBMapper(DB_ORMRepository dbOrm){
        this.dbOrm = dbOrm;
    }

    public ArrayList<Student> getStudents() throws Exception {
        dbOrm.connect();

        ArrayList<Student> students = dbOrm.getStudents()
                .stream()
                .map(StudentFromDbMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));

        dbOrm.close();

        return students;
    }

    public ArrayList<TaskResult> getTaskResults() throws Exception {
        dbOrm.connect();

        ArrayList<TaskResult> taskResults = dbOrm.getTaskResults()
                .stream()
                .map(taskResultEntity ->
                {
                    try {
                        return TaskResultFromDbMapper.map(
                                taskResultEntity,
                                dbOrm.createTaskByTaskID(taskResultEntity.getTaskID()),
                                dbOrm.createStudentByStudentID(taskResultEntity.getStudentID()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));

        dbOrm.close();

        return taskResults;
    }

    public ArrayList<Topic> getTopics() throws Exception{
        dbOrm.connect();

        ArrayList<Topic> students = dbOrm.getTopics()
                .stream()
                .map(TopicFromDbMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));

        dbOrm.close();

        return students;
    }

    public ArrayList<Task> getTasks() throws Exception{
        dbOrm.connect();

        ArrayList<Task> tasks = dbOrm.getTasks()
                .stream()
                .map(taskEntity ->
                {
                    try {
                        return TaskFromDbMapper.map(
                                taskEntity,
                                dbOrm.createTopicByTopicID(taskEntity.getTopicID()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));

        dbOrm.close();

        return tasks;
    }
}
