package org.ulearn.analytics;

public class Main {
    private static Data data;

    public static void main(String[] args) {
        var csvFileName = "basicprogramming_2_1.csv";
        var csvReader = new CSVReader();
        data = csvReader.buildDataFrom(csvFileName);
        printTopics(data);
        //printTasks(data);
        //printStudents(data);
    }

    private static void printTopics(Data data){
        for (var topic : data.getTopics()){
            System.out.println(topic.toString());
        }
    }

    private static void printTasks(Data data){
        for (var task : data.getTasks()){
            System.out.println(task.toString());
        }
    }

    private static void printStudents(Data data){
        for (var student : data.getStudents()){
            System.out.println(student.toString());
        }
    }
}
