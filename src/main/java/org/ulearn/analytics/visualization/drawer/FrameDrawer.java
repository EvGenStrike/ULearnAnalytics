package org.ulearn.analytics.visualization.drawer;

import org.ulearn.analytics.db.mapper.DBMapper;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.models.Task;
import org.ulearn.analytics.models.TaskResult;
import org.ulearn.analytics.statisticsBuilder.StatisticsBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrameDrawer {
    public static void createFrame(DBMapper dbMapper) throws Exception {
        ArrayList<Student> students = dbMapper.getStudents();

        ArrayList<TaskResult> taskResults = dbMapper.getTaskResults();
        HashMap<String, Double> avgScoreByCity = StatisticsBuilder.buildAvgScoreByCityStatistics(taskResults);

        ArrayList<Task> tasks = dbMapper.getTasks();
        Map<String, Double> topicPercentageCompletion
                = StatisticsBuilder.buildTopicPercentageCompletion(taskResults, tasks);

        SwingUtilities.invokeLater(() -> {

            JFrame mainFrame = new JFrame("Главное окно");
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
}
