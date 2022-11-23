package edu.kmaooad.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document("dataviews")
public class DataView {
    @Id
    private String id;
    @DBRef
    private Set<Group> groups;
}
