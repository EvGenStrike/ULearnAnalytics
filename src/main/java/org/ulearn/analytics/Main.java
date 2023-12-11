package org.ulearn.analytics;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.ulearn.analytics.db.DBRepository;
import org.ulearn.analytics.db.DB_ORMRepository;

public class Main {
    private static Data data;

    public static void main(String[] args) throws Exception{
        var csvFileName = "basicprogramming_2_1.csv";
        var csvReader = new CSVReader();
        data = csvReader.buildDataFrom(csvFileName);
        var vkReader = new VkReader(data);
        vkReader.addCitiesToStudents();

        //printTopics(data);
        //printTasks(data);
        //printStudents(data);

        //DBRepository.connect();
        //DBRepository.createTableStudents();
        //DBRepository.saveStudents(data.getStudents());
        //System.out.println(DBRepository.getStudents());

        var dbOrm = new DB_ORMRepository();
        dbOrm.connect();
        dbOrm.createTable();
        dbOrm.saveStudents(data.getStudents());

        System.out.println(dbOrm.getStudents());
        System.out.println(dbOrm.getStudentsByFullName("Евгений", "Петряков"));

        dbOrm.close();
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
