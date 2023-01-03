package edu.kmaooad.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Builder
@Document("students")
public class Student {
    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;

    private String groupId;

    private String dep;
    private String org;

    private List<String> activities;
}