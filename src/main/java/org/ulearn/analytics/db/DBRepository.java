package org.ulearn.analytics.db;

import org.ulearn.analytics.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepository {
    private static Connection connection = null;
    private static final String URL = "jdbc:sqlite:F:\\topsecrets\\coding\\Projects\\Ulearn_BP_Analytics\\db\\test.db";

    public static void connect(){
        try{
            connection = DriverManager.getConnection(URL);
            System.out.println("Соединение с SQLite было установлено");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException ex){
                System.out.printf("Connection error - %s", ex.getMessage());
            }
        }
    }

    public static void createTableStudents(){
        String sql = "CREATE TABLE IF NOT EXISTS students (\n" +
                " id integer PRIMARY KEY,\n" +
                " name text NOT NULL,\n" +
                " academic_group text NOT NULL,\n" +
                " city text\n" +
                ");";

        try(Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table students created");
        }
        catch (SQLException e){
            System.out.printf("Error on create table Students - %s", e.getMessage());
        }
    }

    public static void saveStudents(List<Student> students){
        String sql = "INSERT INTO students(name, academic_group, city) values(?, ?, ?)";

        for (var student: students){
            try (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, student.getFullName());
                preparedStatement.setString(2, student.getGroup());
                preparedStatement.setString(3, student.getCity());
                preparedStatement.executeUpdate();
                System.out.printf("Student " + student.getFullName() + " added%n9");
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Student> getStudents(){
        String sql = "SELECT name, academic_group, city FROM students";

        List<Student> students = new ArrayList<Student>();

        try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                String fullName = resultSet.getString("name");
                String group = resultSet.getString("academic_group");
                String city = resultSet.getString("city");

                String[] splittedFullName = fullName.split(" ");
                Student student;
                if (splittedFullName.length != 2){
                    student = new Student("", splittedFullName[0], group, city);
                }
                else{
                    student = new Student(splittedFullName[1], splittedFullName[0], group, city);
                }
                students.add(student);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return students;
    }
}
