package edu.kmaooad.services.implementations;

import edu.kmaooad.models.Activity;
import edu.kmaooad.models.Student;
import edu.kmaooad.repository.ActivityRepository;
import edu.kmaooad.repository.GroupRepository;
import edu.kmaooad.repository.StudentRepository;
import edu.kmaooad.services.interfaces.CompetencesSummaryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompetencesSummaryServiceImpl implements CompetencesSummaryService {
    private final GroupRepository groupRepository;

    private final StudentRepository studentRepository;

    private final ActivityRepository activityRepository;

    public CompetencesSummaryServiceImpl(
            GroupRepository groupRepository,
            StudentRepository studentRepository,
            ActivityRepository activityRepository
    ) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Map<String, Long> getCompetencesSummaryForStudentGroup(String groupId) throws Exception {
        List<Student> students = groupRepository.findById(groupId)
                .orElseThrow(() -> new Exception("no group with id " + groupId))
                .getStudents()
                .stream()
                .map(studentId -> studentRepository.findById(studentId).orElseThrow())
                .collect(Collectors.toList());

//        List<List<List<String>>> skillsOfAllStudents = new ArrayList<>();
//
//        for (Student student : students) {
//            List<List<String>> skillsOfStudent = student
//                    .getActivities()
//                    .stream()
//                    .map(activityId -> activityRepository.findById(activityId).orElseThrow().getSkills())
////                    .flatMap(List::stream)
////                    .distinct()
//                    .collect(Collectors.toList());
//
//            skillsOfAllStudents.add(skillsOfStudent);
//        }
//
//        return skillsOfAllStudents
//                .stream()
//                .flatMap(List::stream)
//                .flatMap(List::stream)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return students
                .stream()
                .map(student -> student
                                  .getActivities()
                                  .stream()
                                  .map(activityId -> activityRepository
                                                       .findById(activityId)
                                                       .orElseThrow()
                                                       .getSkills()
                                  )
                                  .flatMap(List::stream)
                                  .distinct()
                                  .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
