package edu.kmaooad.repository;

import edu.kmaooad.dto.StudentFilterDTO;
import edu.kmaooad.models.Student;
import edu.kmaooad.utils.CriteriaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Stream;

public class StudentCriteriaRepositoryImpl implements StudentCriteriaRepository {

	private static final String EMAIL = "email";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String PATRONYMIC = "patronymic";
	private static final String DEP = "dep";
	private static final String GROUP = "group";
	private static final String SKILLS = "skills";
	private static final String ACTIVITIES = "activities";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Student> search(StudentFilterDTO filter) {
		final var query = new Query();

		final var primitiveCriterias = List.of(
				CriteriaUtil.getCriteriaByNotBlankString(EMAIL, filter.getEmail()),
				CriteriaUtil.getCriteriaByNotBlankString(FIRST_NAME, filter.getFirstName()),
				CriteriaUtil.getCriteriaByNotBlankString(LAST_NAME, filter.getLastName()),
				CriteriaUtil.getCriteriaByNotBlankString(PATRONYMIC, filter.getPatronymic()),
				CriteriaUtil.getCriteriaByNotBlankString(DEP, filter.getDep()),
				CriteriaUtil.getCriteriaByNotBlankString(GROUP, filter.getGroup())
		);

//		final var listCriteriasOld = CriteriaUtil.getCriteriasByPresenceInList(SKILLS, filter.getSkills());
		final var listCriterias = CriteriaUtil.getCriteriasActivities(filter.getActivities());

		final var criterias = Stream.concat(primitiveCriterias.stream(), listCriterias.stream())
				.toArray(Criteria[]::new);

		query.addCriteria(new Criteria().andOperator(criterias));


		return mongoTemplate.find(query, Student.class);
	}
}
