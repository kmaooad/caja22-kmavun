package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document("groups")
public class Group {
    @Id
    private String name;
    @DBRef
    private Set<Project> competences;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Project> competences) {
        this.competences = competences;
    }
}
