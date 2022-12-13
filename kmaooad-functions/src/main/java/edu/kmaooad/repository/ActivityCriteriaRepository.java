package edu.kmaooad.repository;

import edu.kmaooad.dto.ActivityFilterDTO;
import edu.kmaooad.models.Activity;

import java.util.List;

public interface ActivityCriteriaRepository {
    List<Activity> search(ActivityFilterDTO filter);
}
