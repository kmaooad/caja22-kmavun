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

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(Set<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }
}
