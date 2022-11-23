package edu.kmaooad.models;

import edu.kmaooad.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("activities")
public class Activity {
    @Id
    private String id;
    private String name;
    private Enum<Status> status;
    private String group;
    private String dep;
    private String org;
}
