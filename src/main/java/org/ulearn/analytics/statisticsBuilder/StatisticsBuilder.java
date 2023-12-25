package org.ulearn.analytics.statisticsBuilder;

import org.ulearn.analytics.models.Task;
import org.ulearn.analytics.models.TaskResult;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsBuilder {
    public static HashMap<String, Double> buildAvgScoreByCityStatistics(ArrayList<TaskResult> taskResults){
        HashMap<String, ArrayList<Integer>> avgScoreByCity = new HashMap<>();

        for (TaskResult taskResult: taskResults) {
            String city = taskResult.getStudent().getCity();
            if (taskResult.getStudent().getCity() == null || taskResult.getStudent().getCity().isEmpty()){
                continue;
            }

            if (!avgScoreByCity.containsKey(city)){
                avgScoreByCity.put(city, new ArrayList<>());
            }
            avgScoreByCity.get(city).add(taskResult.getCurrentPoints());
        }

        return (HashMap<String, Double>) avgScoreByCity.entrySet().stream()
                .map(
                        entry ->
                        new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                entry.getValue().stream()
                                    .reduce(0, Integer::sum) / (double)entry.getValue().size()
                        )
                )
                .collect(
                        Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)
                );
    }

    public static Map<String, Double> buildTopicPercentageCompletion(
            ArrayList<TaskResult> taskResults,
            ArrayList<Task> tasks
    ){
        HashMap<String, ArrayList<Integer>> topicCompletion = new HashMap<>();
        Map<String, Integer> maxPointsForTopics = getMaxPointsForTopic(tasks);

        for (TaskResult taskResult: taskResults){
            String topicName = taskResult.getTask().getTopic().getTopicName();

            if (!topicCompletion.containsKey(topicName)){
                topicCompletion.put(topicName, new ArrayList<>());
            }
            if (taskResult.getCurrentPoints() != 0){
                topicCompletion.get(topicName).add(taskResult.getCurrentPoints());
            }
        }

        var topicPercentageCompletion = (HashMap<String, Double>) topicCompletion.entrySet().stream()
                .map(
                    entry -> new AbstractMap.SimpleEntry<>(
                            entry.getKey(),
                            entry.getValue().stream()
                                    .reduce(0, Integer::sum) / ((double)entry.getValue().size()
                                                * maxPointsForTopics.get(entry.getKey()))
                            )
                )
                .collect(
                        Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)
                );
        Map<String, Double> result = new LinkedHashMap<>();
        for (String topicName: maxPointsForTopics.keySet()){
            result.put(topicName, topicPercentageCompletion.get(topicName));
        }
        return result;
    }

    public static Map<String, Integer> getMaxPointsForTopic(ArrayList<Task> tasks){
        Map<String, Integer> maxPointsForTopics = new LinkedHashMap<>();

        for (Task task: tasks){
            String topicName = task.getTopic().getTopicName();
            if (!maxPointsForTopics.containsKey(topicName)){
                maxPointsForTopics.put(topicName, 0);
            }
            maxPointsForTopics.put(topicName, maxPointsForTopics.get(topicName) + task.getMaxPoints());
        }

        return maxPointsForTopics;
    }
}
