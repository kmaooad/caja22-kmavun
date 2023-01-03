package edu.kmaooad.services;

import edu.kmaooad.dto.ActivityFilterDTO;
import edu.kmaooad.models.Activity;

import java.util.List;

public interface ActivityService {
    void saveActivity(Activity activity);

    List<Activity> getAllActivities();
    List<Activity> searchActivities(ActivityFilterDTO filter);
    Activity getById(String id) throws Exception;
}
