package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("students")
public class Student {
    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;

    @DBRef
    private Group group;

    private String dep;
    private String org;

    @DBRef
    private List<Activity> activities;
}