package edu.kmaooad.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StudentFilterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dep;
    private String group;
    private List<String> skillNames;
    private List<String> skillIds;
}
