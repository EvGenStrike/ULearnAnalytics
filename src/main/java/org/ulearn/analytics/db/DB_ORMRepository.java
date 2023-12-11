package org.ulearn.analytics.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.ulearn.analytics.Student;
import org.ulearn.analytics.db.model.StudentCityEntity;

import java.sql.SQLException;
import java.util.List;

public class DB_ORMRepository {
    private final String URL = "jdbc:sqlite:F:\\topsecrets\\coding\\Projects\\Ulearn_BP_Analytics\\db\\test.db";

    private ConnectionSource connectionSource = null;

    private Dao<StudentCityEntity, String> studentDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        studentDao = DaoManager.createDao(connectionSource, StudentCityEntity.class);
    }

    public void createTable() throws SQLException{
        TableUtils.createTableIfNotExists(connectionSource, StudentCityEntity.class);
    }

    public void saveStudents(List<Student> students) throws SQLException{
        for (Student student: students){
            System.out.println(student);
            StudentCityEntity studentCityEntity = new StudentCityEntity(
                    student.getName(), student.getSurname(), student.getGroup(), student.getCity());
            studentDao.createIfNotExists(studentCityEntity);
        }
    }

    public List<StudentCityEntity> getStudents() throws SQLException{
        return studentDao.queryForAll();
    }

    public List<StudentCityEntity> getStudentsByFullName(String name, String surname) throws SQLException{
        return studentDao.queryBuilder()
                .where()
                .eq(StudentCityEntity.NAME_COLUMN, name)
                .eq(StudentCityEntity.SURNAME_COLUMN, surname)
                .query();
    }

    public void close() throws Exception{
        connectionSource.close();
    }
}
