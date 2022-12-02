package edu.kmaooad.repository;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentCriteriaRepositoryImpl implements StudentCriteriaRepository {
    @Override
    public List<Student> search(StudentFilterDTO filter) {
        return new ArrayList<>();
    }
}
