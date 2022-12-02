package edu.kmaooad.dto;

import lombok.Data;

@Data
public class StudentFilterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dep;
    private String group;
}
