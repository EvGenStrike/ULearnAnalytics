package org.ulearn.analytics;

public class Student {
    private String name;
    private String surname;
    private String group;
    private TaskResult[] taskResults;

    public Student(String name, String surname, String group){
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
}
