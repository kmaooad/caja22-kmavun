package edu.kmaooad.service;

import edu.kmaooad.dto.ActivityFilterDTO;
import edu.kmaooad.models.Activity;
import edu.kmaooad.repository.ActivityRepository;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> searchActivities(ActivityFilterDTO filter) {
        return activityRepository.search(filter);
    }

    @Override
    public Activity getById(String id) throws Exception {
        return activityRepository.getById(id).orElseThrow(() -> new Exception("Activity id: " + id + " has not been found"));
    }
}
