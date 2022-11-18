package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("students")
public class Student {

    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String patronym;
    private Integer group;
    private String dep;
    private String org;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronym() {
        return patronym;
    }

    public Integer getGroup() {
        return group;
    }

    public String getDep() {
        return dep;
    }

    public String getOrg() {
        return org;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setOrg(String org) {
        this.org = org;
    }
}
