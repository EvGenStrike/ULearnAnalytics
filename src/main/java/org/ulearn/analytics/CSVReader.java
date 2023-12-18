package org.ulearn.analytics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    private static final char CUSTOM_DELIMITER = ';';

    private ArrayList<Topic> topics = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<TaskResult> taskResults = new ArrayList<>();

    public Data buildDataFrom(String csvFileName){
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(CUSTOM_DELIMITER);
        try (FileReader fileReader = new FileReader(csvFileName);
             CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {
            var i = 0;
            List<String> topicsCSV = new ArrayList<>();
            List<String> taskNamesCSV = new ArrayList<>();
            List<String> maxPointsCSV = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                var values = csvRecord.values();
                switch (i){
                    case 0:
                        topicsCSV = Arrays.asList(values);
                        break;
                    case 1:
                        taskNamesCSV = Arrays.asList(values);
                        break;
                    case 2:
                        maxPointsCSV = Arrays.asList(values);
                        break;
                    default:
                        break;
                }
                if (i == 2){
                    topics = buildTopics(topicsCSV, taskNamesCSV, maxPointsCSV);
                    tasks = buildTasks(topics);
                }
                else if (i > 2){
                    var student = buildStudent(values);
                    students.add(student);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Data(topics, students, tasks, taskResults);
    }

    private ArrayList<Topic> buildTopics(
            List<String> topicsCSV,
            List<String> taskNamesCSV,
            List<String> maxPointsCSV
    ){
        var topics = new ArrayList<Topic>();
        var startPosition = 2;
        Topic currentTopic = null;
        for (var i = startPosition; i < topicsCSV.size(); i++){
            var topicName = topicsCSV.get(i);
            if (!topicName.isEmpty()){
                currentTopic = new Topic(topicName);
                topics.add(currentTopic);
            }

            var taskNameCSV = taskNamesCSV.get(i);
            var taskType = getTaskType(taskNameCSV);
            var maxPoints = Integer.parseInt(maxPointsCSV.get(i));
            var taskName = getTaskName(taskNameCSV);
            var task = new Task(taskType, maxPoints, currentTopic, taskName);
            currentTopic.addTask(task);
        }

        return topics;
    }

    private TaskType getTaskType(String taskNameCSV){
        var delimiterIndex = taskNameCSV.indexOf(':');
        var taskTypeStr = taskNameCSV.substring(
                0, delimiterIndex != -1
                    ? delimiterIndex
                    : taskNameCSV.length());
        switch (taskTypeStr){
            case "Акт":
                return TaskType.ACTIVITY;
            case "Упр":
                return TaskType.EXERCISE;
            case "ДЗ":
                return TaskType.HOMEWORK;
            case "Сем":
                return TaskType.SEMINAR;
            default:
                return null;
        }
    }

    private String getTaskName(String taskNameCSV){
        var delimiterIndex = taskNameCSV.indexOf(':');
        return delimiterIndex == -1
                ? null
                : taskNameCSV.substring(delimiterIndex + 2);
    }

    private ArrayList<Task> buildTasks(ArrayList<Topic> topics){
        var tasks = new ArrayList<Task>();
        for (var topic : topics){
            tasks.addAll(topic.getTasks());
        }
        return tasks;
    }

    private Student buildStudent(String[] values){
        var splittedNameSurname = values[0].split(" ");
        var surname = splittedNameSurname[0].strip();
        var name = "";
        if (splittedNameSurname.length == 2){
            name = splittedNameSurname[1].strip();
        }
        if (splittedNameSurname.length > 2){
            name = values[0].substring(values[0].indexOf(" ")).strip();
        }
        var group = values[1];
        var student = new Student(name, surname, group);

        var i = 2;
        for (var task : tasks){
            var taskResult = new TaskResult(task, Integer.parseInt(values[i]), student);
            taskResults.add(taskResult);
            student.addTaskResult(taskResult);
            i++;
        }

        return student;
    }
}
