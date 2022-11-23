package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("topics")
public class Topic {
    @Id
    private String topicID;
    private String topicName;
    @DBRef
    private Topic parentTopic;
}
