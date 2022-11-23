package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Data
@Document("projects")
public class Project {
    @Id
    private String projectID;
    private String projectTitle;
    private String projectDescription;
    @DBRef
    private Topic topic;
    @DBRef
    private Set<Skill> skills;
    @DBRef
    private Set<SkillSet> skillSets;
    @DBRef
    private Set<Student> students;
    @DBRef
    private Activity activity;
}
