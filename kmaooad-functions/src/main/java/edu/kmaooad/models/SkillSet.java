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
    @DBRef
    private Set<SkillSet> skillSet;

    public String getSkillSetID() {
        return skillSetID;
    }

    public void setSkillSetID(String skillSetID) {
        this.skillSetID = skillSetID;
    }

    public String getSkillSetName() {
        return skillSetName;
    }

    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public Set<SkillSet> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<SkillSet> skillSet) {
        this.skillSet = skillSet;
    }
}
