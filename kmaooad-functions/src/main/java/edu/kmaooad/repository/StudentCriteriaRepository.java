package edu.kmaooad.repository;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Student;

import java.util.List;

public interface StudentCriteriaRepository {
    List<Student> search(StudentFilterDTO filter);
}
