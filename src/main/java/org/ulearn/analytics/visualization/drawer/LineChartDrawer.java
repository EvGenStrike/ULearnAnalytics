package org.ulearn.analytics.visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.ulearn.analytics.visualization.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LineChartDrawer {
    public static JFrame createLineChartFrame(String title, HashMap<String, Double> topicPercentageCompletion) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = createTopicPercentageCompletionPanel(topicPercentageCompletion);
        frame.setContentPane(panel);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return frame;
    }

    public static JPanel createTopicPercentageCompletionPanel(Map<String, Double> topicPercentageCompletion) {
        JFreeChart chart = createLineChart(
                ChartDataMapper.createTopicPercentageCompletionDataset(topicPercentageCompletion));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    public static JFreeChart createLineChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "% прохождения темы",
                "Название темы",
                "Процент",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setMaximumCategoryLabelLines(3);

        chart.setBackgroundPaint(Color.WHITE);

        return chart;
    }
}
