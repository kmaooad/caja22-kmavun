package edu.kmaooad;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Group;
import edu.kmaooad.models.Student;
import edu.kmaooad.models.Activity;
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
        Activity a1 = Activity.builder()
                .id("1")
                .name("Football")
                .skills(List.of("Python"))
                .build();

        Activity a2 = Activity.builder()
                .id("2")
                .name("Basketball")
                .skills(List.of("Java"))
                .build();
        Student student1 = Student.builder()
                .id("1")
                .dep("FI")
                .firstName("Yarema")
                .activities(List.of(a1))
                .email("yaremamed@gmail.com")
                .build();
        studentRepository.save(student1);
        Student student2 = Student.builder()
                .id("2")
                .dep("FI")
                .firstName("Nikmas")
                .activities(List.of(a2))
                .email("nikmasdev@gmail.com")
                .build();
        studentRepository.save(student2);
        Student student3 = Student.builder()
                .id("3")
                .dep("FI")
                .firstName("Yaroslav")
                .activities(List.of(a1, a2))
                .email("yaroslav@gmail.com")
                .build();
        studentRepository.save(student3);
        Student student4 = Student.builder()
                .id("4")
                .dep("Social")
                .firstName("Maksym")
                .activities(List.of(a1))
                .email("maksym@gmail.com")
                .build();
        studentRepository.save(student4);

        // works when StudentCriteriaRepositoryImpl 40 using names instead of ids
        List list = studentRepository.search(StudentFilterDTO.builder().activities(List.of(a1)).build());
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
