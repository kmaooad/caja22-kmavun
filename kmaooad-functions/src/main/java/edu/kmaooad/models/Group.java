package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("groups")
public class Group {
    @Id
    private String id;

    private String name;

    @DBRef
    private List<Student> students;
}
