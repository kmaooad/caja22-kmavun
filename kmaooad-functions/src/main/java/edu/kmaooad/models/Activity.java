package edu.kmaooad.models;

import edu.kmaooad.enums.ActivityStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("activities")
public class Activity {
    @Id
    private String id;

    private String name;
    private ActivityStatus status;
    private List<String> skills;

    @DBRef
    private Group group;

    private String dep;
    private String org;
}
