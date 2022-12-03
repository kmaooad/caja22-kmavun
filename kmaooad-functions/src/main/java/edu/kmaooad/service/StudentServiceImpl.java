package edu.kmaooad.service;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Skill;
import edu.kmaooad.models.Student;
import edu.kmaooad.repository.SkillRepository;
import edu.kmaooad.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;

    public StudentServiceImpl(StudentRepository studentRepository, SkillRepository skillRepository) {
        this.studentRepository = studentRepository;
        this.skillRepository = skillRepository;
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
        if (isEmpty(filter.getSkillIds()) && !isEmpty(filter.getSkillNames())) {
            final var skillIds = skillRepository.findByNameIn(filter.getSkillNames()).stream()
                    .map(Skill::getId)
                    .collect(Collectors.toList());
            filter.setSkillIds(skillIds);
        }
        return studentRepository.search(filter);
    }

    @Override
    public Student getById(String id) throws Exception {
        return studentRepository.getById(id)
                .orElseThrow(() -> new Exception("Student id" + id));
    }

}
