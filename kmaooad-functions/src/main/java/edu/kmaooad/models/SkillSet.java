package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document("skillsSet")
public class SkillSet {
    @Id
    private String skillSetID;
    private String skillSetName;
    private Set<String> skills;
}
