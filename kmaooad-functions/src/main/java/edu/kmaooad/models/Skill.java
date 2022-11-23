package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("skills")
public class Skill {
    @Id
    private String skillID;
    private String skillName;
    @DBRef
    private Skill parentSkill;
}
