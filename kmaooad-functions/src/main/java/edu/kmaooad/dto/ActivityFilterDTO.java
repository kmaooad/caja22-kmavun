package edu.kmaooad.dto;

import edu.kmaooad.enums.ActivityStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ActivityFilterDTO {
    private String name;
    private ActivityStatus status;
    private List<String> skills;
    private String dep;
    private String org;
}
