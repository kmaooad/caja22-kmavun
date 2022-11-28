package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("projects")
public class Project {
    @Id
    private String id;

    private String title;
    private String description;

    @DBRef
    private Topic topic;

    @DBRef
    private List<Skill> skills;
}
