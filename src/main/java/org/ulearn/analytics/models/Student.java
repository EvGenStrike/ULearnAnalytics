package org.ulearn.analytics.models;

import java.util.ArrayList;

public class Student {
    private String name;
    private String surname;
    private String group;
    private String city;
    private ArrayList<TaskResult> taskResults = new ArrayList<>();

    public Student(String name, String surname, String group){
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public Student(String name, String surname, String group, String city){
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.city = city;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getFullName(){
        return String.format("%s %s", surname, name);
    }

    public String getGroup(){
        return group;
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void addTaskResult(TaskResult taskResult){
        taskResults.add(taskResult);
    }

    public void removeTaskResult(TaskResult taskResult){
        taskResults.remove(taskResult);
    }

    public ArrayList<TaskResult> getTaskResults(){
        return taskResults;
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("%s %s;group=%s;city=%s;", name, surname, group, city));
//        for (var taskResult : taskResults){
//            sb.append(String.format("%n%s%n", taskResult.toString()));
//        }
        return sb.toString();
    }
}
