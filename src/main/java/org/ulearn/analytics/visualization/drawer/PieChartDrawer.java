package org.ulearn.analytics.visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.visualization.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PieChartDrawer {
    public static JFrame createPieChartFrame(String title, ArrayList<Student> students) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = createStudentsByCitiesPanel(students);
        frame.setContentPane(panel);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return frame;
    }

    public static JPanel createStudentsByCitiesPanel(ArrayList<Student> students) {
        JFreeChart chart = createPieChart(ChartDataMapper.createStudentByCityDataset(students));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Студенты по городам",
                dataset,
                false,
                true,
                false
        );

        chart.setBackgroundPaint(new GradientPaint(
                new Point(0, 0),
                new Color(20, 20, 20),
                new Point(400, 200),
                Color.DARK_GRAY
        ));

        TextTitle textTitle = chart.getTitle();
        textTitle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        textTitle.setPaint(new Color(240, 240, 240));
        textTitle.setFont(new Font("Arial", Font.BOLD, 26));
        textTitle.setText("Студенты по городам");

        return chart;
    }
}