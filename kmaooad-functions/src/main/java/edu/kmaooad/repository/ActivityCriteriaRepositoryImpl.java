package edu.kmaooad.repository;

import edu.kmaooad.dto.ActivityFilterDTO;
import edu.kmaooad.models.Activity;
import edu.kmaooad.utils.CriteriaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Stream;

public class ActivityCriteriaRepositoryImpl implements ActicityCriteriaRepository {
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String SKILLS = "skills";
    private static final String DEP = "dep";
    private static final String ORG = "org";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Activity> search(ActivityFilterDTO filter) {
        final var query = new Query();

        final var primitiveCriterias = List.of(CriteriaUtil.getCriteriaByNotBlankString(NAME, filter.getName()),
                CriteriaUtil.getCriteriaByNotBlankString(STATUS, filter.getStatus().toString()),
                CriteriaUtil.getCriteriaByNotBlankString(DEP, filter.getDep()),
                CriteriaUtil.getCriteriaByNotBlankString(ORG, filter.getOrg()));

        final var listCriterias = CriteriaUtil.getCriteriasByPresenceInList(SKILLS, filter.getSkills());

        final var criterias = Stream.concat(primitiveCriterias.stream(), listCriterias.stream())
                .toArray(Criteria[]::new);

        query.addCriteria(new Criteria().andOperator(criterias));
        return mongoTemplate.find(query, Activity.class);
    }
}
