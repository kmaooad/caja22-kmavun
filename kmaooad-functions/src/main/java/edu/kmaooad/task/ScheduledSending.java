package edu.kmaooad.task;

import edu.kmaooad.repository.ActivityRepository;
import edu.kmaooad.repository.GroupRepository;
import edu.kmaooad.repository.StudentRepository;
import edu.kmaooad.services.EmailServiceImpl;
import edu.kmaooad.services.implementations.CompetencesSummaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

@Configuration
@EnableScheduling
public class ScheduledSending {


    private StudentRepository studentRepository;
    private GroupRepository groupRepository;
    private ActivityRepository activityRepository;

    private String[] emailSendPool = new String[]{"maxdiach137@gmail.com"};
    public ScheduledSending(
            GroupRepository groupRepository,
            StudentRepository studentRepository,
            ActivityRepository activityRepository
    ) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.activityRepository = activityRepository;
    }
    @Scheduled(fixedRate = 24*60*60*1000)
    public void emailNotificationCompetences() throws Exception {
        EmailServiceImpl mailService = new EmailServiceImpl();
        String message = "Competences Summary Results:";
        CompetencesSummaryServiceImpl service = new CompetencesSummaryServiceImpl(groupRepository,studentRepository,activityRepository);
        Map<String,Long> competences = service.getCompetencesSummaryForStudentGroup("1");
        for (Map.Entry<String, Long> e: competences.entrySet()) {
            message+="\n"+e.getKey()+" | "+e.getValue();
        }
        for(int i=0; i< emailSendPool.length; i++){
            mailService.sendSimpleMessage(emailSendPool[i],"Notification",message);
        }
        System.out.println("Sent message at "+System.currentTimeMillis());
    }
}
