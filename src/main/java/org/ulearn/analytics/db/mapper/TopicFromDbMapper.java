package org.ulearn.analytics.db.mapper;

import org.ulearn.analytics.db.model.TopicEntity;
import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.db.model.StudentEntity;
import org.ulearn.analytics.models.Topic;

public class TopicFromDbMapper {
    public static Topic map(TopicEntity topic){
        return new Topic(topic.getTopicName());
    }
}
