package edu.kmaooad.models;

import edu.kmaooad.enums.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Builder
@Document("activities")
public class Activity {
    @Id
    private String id;

    private String name;
    private Enum<Status> status;
    private List<String> skills;

    private String groupId;

    private String dep;
    private String org;
}
