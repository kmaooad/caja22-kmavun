package edu.kmaooad;

import edu.kmaooad.models.Student;
import edu.kmaooad.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OoadSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OoadSpringApplication.class, args);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
//        Student student = Student.builder()
//                .id("1")
//                .dep("FI")
//                .email("yaremamed@gmail.com")
//                .build();
//        studentRepository.save(student);
        System.out.println(studentRepository.getById("1"));
    }
}
