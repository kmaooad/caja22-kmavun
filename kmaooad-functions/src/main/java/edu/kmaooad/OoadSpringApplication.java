package edu.kmaooad;

import edu.kmaooad.enums.Status;
import edu.kmaooad.models.Activity;
import edu.kmaooad.models.Group;
import edu.kmaooad.models.Student;
import edu.kmaooad.repository.*;
import edu.kmaooad.services.EmailServiceImpl;
import edu.kmaooad.services.implementations.CompetencesSummaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class OoadSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OoadSpringApplication.class, args);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ActivityRepository activityRepository;


    @Override
    public void run(String... args) throws Exception {

        EmailServiceImpl mailService = new EmailServiceImpl();
        mailService.sendSimpleMessage("maxdiach137@gmail.com","test","test");

        Activity frontend = Activity
                .builder()
                .id("1")
                .name("frontend")
                .status(Status.IN_PROGRESS)
                .skills(List.of("js", "react"))
                .dep("FI")
                .org("Naukma")
                .build();

        Activity backend = Activity
                .builder()
                .id("2")
                .name("backend")
                .status(Status.IN_PROGRESS)
                .skills(List.of("rust", "rocket"))
                .dep("FI")
                .org("Naukma")
                .build();

        Activity dataScience = Activity
                .builder()
                .id("3")
                .name("data science")
                .status(Status.IN_PROGRESS)
                .skills(List.of("python", "plotly"))
                .dep("FI")
                .org("Naukma")
                .build();

        activityRepository.save(frontend);
        activityRepository.save(backend);
        activityRepository.save(dataScience);

        Student student1 = Student.builder()
                .id("1")
                .dep("FI")
                .firstName("Yarema")
                .activities(List.of("1", "2"))
                .groupId("1")
                .email("yaremamed@gmail.com")
                .build();

        Student student2 = Student.builder()
                .id("2")
                .dep("FI")
                .firstName("Nikmas")
                .activities(List.of("2", "3"))
                .groupId("1")
                .email("nikmasdev@gmail.com")
                .build();

        Student student3 = Student.builder()
                .id("3")
                .dep("FI")
                .firstName("Yaroslav")
                .activities(List.of("1", "3"))
                .groupId("1")
                .email("yaroslav@gmail.com")
                .build();

        Student student4 = Student.builder()
                .id("4")
                .dep("Social")
                .firstName("Maksym")
                .activities(List.of("1", "2", "3"))
                .groupId("1")
                .email("maksym@gmail.com")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);

        Group group = Group
                .builder()
                .id("1")
                .name("apples")
                .students(List.of("1", "2", "3", "4"))
                .build();

        groupRepository.save(group);

        CompetencesSummaryServiceImpl service = new CompetencesSummaryServiceImpl(
                groupRepository,
                studentRepository,
                activityRepository
        );

        Map<String, Long> competencesSummary = service.getCompetencesSummaryForStudentGroup("1");

        competencesSummary.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
