package edu.kmaooad.models;

import edu.kmaooad.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("activities")
public class Activity {
    @Id
    private String id;

    private String name;
    private Enum<Status> status;
    private List<Project> projects;

    @DBRef
    private Group group;

    private String dep;
    private String org;
}
