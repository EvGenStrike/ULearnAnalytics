package org.ulearn.analytics.visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.ulearn.analytics.visualization.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BarChartDrawer {
    public static JFrame createBarChartFrame(String title, HashMap<String, Double> citiesAvgScore) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = createAverageScoreByCityPanel(citiesAvgScore);
        frame.setContentPane(panel);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return frame;
    }

    public static JPanel createAverageScoreByCityPanel(HashMap<String, Double> citiesAvgScore) {
        JFreeChart chart = createBarChart(ChartDataMapper.createAverageScoreByCityDataset(citiesAvgScore));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    public static JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Средний балл по городу",
                "Город",
                "Средний балл",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                false,
                false
        );

        chart.setBackgroundPaint(Color.WHITE);

        return chart;
    }
}
