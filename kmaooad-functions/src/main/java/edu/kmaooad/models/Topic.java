package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("topics")
public class Topic {
    @Id
    private String id;

    private String name;

    @DBRef
    private Topic parentTopic;
}
