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

    public String getSkillID() {
        return skillID;
    }

    public void setSkillID(String skillID) {
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Skill getParentSkill() {
        return parentSkill;
    }

    public void setParentSkill(Skill parentSkill) {
        this.parentSkill = parentSkill;
    }
}
