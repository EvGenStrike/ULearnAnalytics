package org.ulearn.analytics;

import org.ulearn.analytics.csvParser.CSVReader;
import org.ulearn.analytics.db.DB_ORMRepository;
import org.ulearn.analytics.db.mapper.DBMapper;
import org.ulearn.analytics.models.*;
import org.ulearn.analytics.statisticsBuilder.StatisticsBuilder;
import org.ulearn.analytics.visualization.drawer.BarChartDrawer;
import org.ulearn.analytics.visualization.drawer.LineChartDrawer;
import org.ulearn.analytics.visualization.drawer.PieChartDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    private static Data data;
    private static final DB_ORMRepository dbOrm = new DB_ORMRepository();

    public static void main(String[] args) throws Exception{
//        var csvFileName = "basicprogramming_2_1.csv";
//        data = buildDataFromCSV(csvFileName);

//        var vkReader = new VkReader(data);
//        vkReader.addCitiesToStudents();

//        fillDb();

        visualize();
    }

    private static Data buildDataFromCSV(String csvFileName){
        var csvReader = new CSVReader();
        return csvReader.buildDataFrom(csvFileName);
    }

    private static void fillDb() throws Exception{
        dbOrm.connect();
        dbOrm.createTables();

        System.out.printf("Size of taskResults: %s", data.getTaskResults().size());

        dbOrm.saveStudents(data.getStudents());
        dbOrm.saveTopics(data.getTopics());
        dbOrm.saveTasks(data.getTasks());
        dbOrm.saveTaskResults(data.getTaskResults());

        dbOrm.close();
    }

    private static void visualize() throws Exception {
        DBMapper dbMapper = new DBMapper(dbOrm);

        ArrayList<Student> students = dbMapper.getStudents();

        ArrayList<TaskResult> taskResults = dbMapper.getTaskResults();
        HashMap<String, Double> avgScoreByCity = StatisticsBuilder.buildAvgScoreByCityStatistics(taskResults);

        ArrayList<Task> tasks = dbMapper.getTasks();
        Map<String, Double> topicPercentageCompletion
                = StatisticsBuilder.buildTopicPercentageCompletion(taskResults, tasks);

        SwingUtilities.invokeLater(() -> {

            JFrame mainFrame = new JFrame("Main Frame");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
            mainFrame.setSize(600, 300);

            JTabbedPane tabbedPane = new JTabbedPane();

            JPanel pieChartPanel = PieChartDrawer.createStudentsByCitiesPanel(students);
            tabbedPane.addTab("Студенты по городам", pieChartPanel);

            JPanel barChartPanel = BarChartDrawer.createAverageScoreByCityPanel(avgScoreByCity);
            tabbedPane.addTab("Средний балл по городам", barChartPanel);

            JPanel lineChartPanel = LineChartDrawer.createTopicPercentageCompletionPanel(topicPercentageCompletion);
            tabbedPane.addTab("% прохождения темы", lineChartPanel);

            mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
            mainFrame.setSize(800, 600);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
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
