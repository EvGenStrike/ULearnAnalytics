package org.ulearn.analytics.visualization.mapper;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.models.TaskResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartDataMapper {
    public static PieDataset createStudentByCityDataset(List<Student> students){
        var studentsCountByCities = students.stream()
                .filter(student -> student.getCity() != null)
                .collect(
                        Collectors.groupingBy(
                                Student::getCity,
                                HashMap::new,
                                Collectors.counting()
                        )
                );

        DefaultPieDataset dataset = new DefaultPieDataset();
        
        studentsCountByCities.forEach(dataset::setValue);

        return dataset;
    }

    public static CategoryDataset createAverageScoreByCityDataset(HashMap<String, Double> citiesAvgScore){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        citiesAvgScore.forEach((city, score) -> dataset.setValue(score, "cityAvgScore", city));
        return dataset;
    }

    public static DefaultCategoryDataset createTopicPercentageCompletionDataset(
            Map<String, Double> topicPercentageCompletion){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        topicPercentageCompletion.forEach((topicName, percentage)
                -> dataset.setValue(percentage, "topicPercentageCompletion", topicName));
        return dataset;
    }
}
