package org.ulearn.analytics.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.ulearn.analytics.Task;
import org.ulearn.analytics.TaskType;

@DatabaseTable(tableName = "topic")
public class TopicEntity {
    public static final String TOPIC_NAME_COLUMN = "topicName";

    @DatabaseField(generatedId = true)
    private long topicID;

    @DatabaseField()
    private String topicName;

    public TopicEntity(){}

    public TopicEntity(String topicName){
        this.topicName = topicName;
    }

    @Override
    public String toString(){
        return String.format("TopicEntity{%ntopicName=%s%n}%n", topicName);
    }

    public long getTopicID(){
        return topicID;
    }
}
