package edu.kmaooad.service;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Student;
import org.springframework.data.domain.Page;

import java.util.List;
//
//Search & reports Note: This service requires its own data views and keeping them up-to-date with other services
//        Find students by competences
//        Query students filtered by own fields (e.g. group, org, dept) and competences

public interface StudentService {
    void saveStudent(Student student);

    List<Student> getAllStudents();

    List<Student> searchStudents(StudentFilterDTO filter);

    Student getById(String id) throws Exception;

}
