package org.ulearn.analytics;

import java.util.ArrayList;

public class Data {
    private ArrayList<Topic> topics = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();

    public Data(
            ArrayList<Topic> topics,
            ArrayList<Student> students,
            ArrayList<Task> tasks){
        this.topics = topics;
        this.students = students;
        this.tasks = tasks;
    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append("TOPICS\n");
        for (var topic : topics){
            sb.append(String.format("%s%n", topic.toString()));
        }
        sb.append("STUDENTS\n");
        for (var student : students){
            sb.append(String.format("%s%n", student.toString()));
        }
        sb.append("TASKS\n");
        for (var task : tasks){
            sb.append(String.format("%s%n", task.toString()));
        }
        return sb.toString();
    }
}
