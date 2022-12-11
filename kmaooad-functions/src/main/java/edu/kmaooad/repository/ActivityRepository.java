package edu.kmaooad.repository;

import edu.kmaooad.models.Activity;
import edu.kmaooad.models.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>, ActicityCriteriaRepository {
    Optional<Activity> getById (String id);
    Optional<Activity> getActivitiesByGroup (Group group);
    Optional<Activity> findActivitiesByDep (String dep);
    Optional<Activity> findActivitiesByOrg (String org);
}
