package edu.kmaooad;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Group;
import edu.kmaooad.models.Student;
import edu.kmaooad.repository.StudentCriteriaRepository;
import edu.kmaooad.repository.StudentCriteriaRepositoryImpl;
import edu.kmaooad.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OoadSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OoadSpringApplication.class, args);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = Student.builder()
                .id("1")
                .dep("FI")
                .firstName("Yarema")
                .skills(List.of("Rust", "Python"))
                .email("yaremamed@gmail.com")
                .build();
        studentRepository.save(student1);
        Student student2 = Student.builder()
                .id("2")
                .dep("FI")
                .firstName("Nikmas")
                .skills(List.of("Java", "C++"))
                .email("nikmasdev@gmail.com")
                .build();
        studentRepository.save(student2);
        Student student3 = Student.builder()
                .id("3")
                .dep("FI")
                .firstName("Yaroslav")
                .skills(List.of("Python", "Java"))
                .email("yaroslav@gmail.com")
                .build();
        studentRepository.save(student3);
        Student student4 = Student.builder()
                .id("4")
                .dep("Social")
                .firstName("Maksym")
                .skills(List.of("Python", "JS"))
                .email("maksym@gmail.com")
                .build();
        studentRepository.save(student4);
        System.out.println(studentRepository.getById("4"));
        // works when StudentCriteriaRepositoryImpl 40 using names instead of ids
        List list = studentRepository.search(StudentFilterDTO.builder().skillNames(List.of("Python")).build());
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
