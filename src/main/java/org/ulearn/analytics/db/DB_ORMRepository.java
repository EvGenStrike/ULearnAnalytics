package org.ulearn.analytics.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.ulearn.analytics.Student;
import org.ulearn.analytics.Task;
import org.ulearn.analytics.TaskResult;
import org.ulearn.analytics.Topic;
import org.ulearn.analytics.db.model.StudentEntity;
import org.ulearn.analytics.db.model.TaskEntity;
import org.ulearn.analytics.db.model.TaskResultEntity;
import org.ulearn.analytics.db.model.TopicEntity;

import java.sql.SQLException;
import java.util.List;

public class DB_ORMRepository {
    private final String URL = "jdbc:sqlite:F:\\topsecrets\\coding\\Projects\\Ulearn_BP_Analytics\\db\\test.db";

    private ConnectionSource connectionSource = null;

    private Dao<StudentEntity, String> studentDao = null;
    private Dao<TopicEntity, String> topicDao = null;
    private Dao<TaskEntity, String> taskDao = null;
    private Dao<TaskResultEntity, String> taskResultDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        studentDao = DaoManager.createDao(connectionSource, StudentEntity.class);
        topicDao = DaoManager.createDao(connectionSource, TopicEntity.class);
        taskDao = DaoManager.createDao(connectionSource, TaskEntity.class);
        taskResultDao = DaoManager.createDao(connectionSource, TaskResultEntity.class);
    }

    public void createTables() throws SQLException{
        TableUtils.createTableIfNotExists(connectionSource, StudentEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, TopicEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, TaskEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, TaskResultEntity.class);
    }

    public void saveStudents(List<Student> students) throws SQLException{
        for (Student student: students){
            StudentEntity studentEntity = new StudentEntity(
                    student.getName(), student.getSurname(), student.getGroup(), student.getCity());
            studentDao.createIfNotExists(studentEntity);
        }
    }

    public void saveTopics(List<Topic> topics) throws SQLException{
        for (Topic topic: topics){
            TopicEntity topicEntity = new TopicEntity(
                    topic.getTopicName());
            topicDao.createIfNotExists(topicEntity);
        }
    }

    public void saveTasks(List<Task> tasks) throws SQLException{
        for (Task task: tasks){
            long topicID = getTopicID(task);
            TaskEntity taskEntity = new TaskEntity(
                    task.getTaskName(), task.getTaskType(), task.getMaxPoints(), topicID);
            taskDao.createIfNotExists(taskEntity);
        }
    }

    public void saveTaskResults(List<TaskResult> taskResults) throws SQLException{
        for (TaskResult taskResult: taskResults){
            long taskID = getTaskID(taskResult);
            long studentID = getStudentID(taskResult);
            TaskResultEntity taskResultEntity = new TaskResultEntity(
                    taskResult.getCurrentPoints(), taskID, studentID);
            taskResultDao.createIfNotExists(taskResultEntity);
        }
    }

    public List<StudentEntity> getStudents() throws SQLException{
        return studentDao.queryForAll();
    }

    public List<StudentEntity> getStudentsByFullName(String name, String surname) throws SQLException{
        return studentDao.queryBuilder()
                .where()
                .eq(StudentEntity.NAME_COLUMN, name)
                .and()
                .eq(StudentEntity.SURNAME_COLUMN, surname)
                .query();
    }

    public void close() throws Exception{
        connectionSource.close();
    }

    private long getTopicID(Task task) throws SQLException {
        return topicDao.queryBuilder()
                .where()
                .eq(TopicEntity.TOPIC_NAME_COLUMN, task.getTopic().getTopicName())
                .query()
                .get(0)
                .getTopicID();
    }

    private long getTaskID(TaskResult taskResult) throws SQLException {
        long topicIDByName = getTopicID(taskResult.getTask());


        Where<TaskEntity, String> where = taskDao.queryBuilder().where();
        return where.and(
                where.eq(TaskEntity.TOPIC_ID_COLUMN, topicIDByName),
                where.eq(TaskEntity.TASK_TYPE_COLUMN, taskResult.getTask().getTaskType()),
                where.eq(TaskEntity.MAX_POINTS_COLUMN, taskResult.getTask().getMaxPoints()),
                where.or(
                        where.isNull(TaskEntity.TASK_NAME_COLUMN),
                        where.eq(TaskEntity.TASK_NAME_COLUMN, taskResult.getTask().getTaskName())
                )
                )
                .query()
                .get(0)
                .getTaskID();

//        return taskDao.queryBuilder()
//                .where()
//                .and(4)
//                .eq(TaskEntity.TOPIC_ID_COLUMN, topicIDByName)
//                .eq(TaskEntity.TASK_TYPE_COLUMN, taskResult.getTask().getTaskType())
//                .eq(TaskEntity.MAX_POINTS_COLUMN, taskResult.getTask().getMaxPoints())
//                .or(2)
//                .eq(TaskEntity.TASK_NAME_COLUMN, taskResult.getTask().getTaskName())
//                .isNull(TaskEntity.TASK_NAME_COLUMN)
//                .query()
//                .get(0)
//                .getTaskID();
    }

    private long getStudentID(TaskResult taskResult) throws SQLException {
        return studentDao.queryBuilder()
                .where()
                .eq(StudentEntity.NAME_COLUMN, taskResult.getStudent().getName())
                .and()
                .eq(StudentEntity.SURNAME_COLUMN, taskResult.getStudent().getSurname())
                .and()
                .eq(StudentEntity.GROUP_COLUMN, taskResult.getStudent().getGroup())
                .query()
                .get(0)
                .getStudentID();
    }
}
