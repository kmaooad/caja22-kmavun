package edu.kmaooad.service;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Student;
import edu.kmaooad.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudents(StudentFilterDTO filter) {
        return studentRepository.search(filter);
    }

    @Override
    public Student getById(String id) throws Exception {
        return studentRepository.getById(id)
                .orElseThrow(() -> new Exception("Student id" + id));
    }

}
