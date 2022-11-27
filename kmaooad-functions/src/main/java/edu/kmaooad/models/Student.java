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
    private String groupId;
    private String dep;
    private String org;
    private Set<String> projects;
}
