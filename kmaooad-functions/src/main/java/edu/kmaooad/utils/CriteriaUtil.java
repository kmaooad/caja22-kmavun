package edu.kmaooad.utils;

import edu.kmaooad.models.Activity;
import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

@UtilityClass
public class CriteriaUtil {

	public static final Criteria EMPTY_CRITERIA = new Criteria();

	public static Criteria getCriteriaByNotBlankString(String key, String value) {
		return isNotBlank(value)
				? Criteria.where(key).is(value)
				: EMPTY_CRITERIA;
	}

	public static List<Criteria> getCriteriasByPresenceInList(String key, List<String> values) {
		if (isEmpty(values)) {
			return List.of(EMPTY_CRITERIA);
		} else {
			return values.stream()
					.map(it -> Criteria.where(key).is(it))
					.collect(Collectors.toList());
		}
	}
		public static List<Criteria> getCriteriasActivities(List<Activity> values) {
			if (isEmpty(values)) {
				return List.of(EMPTY_CRITERIA);
			} else {
				return values.stream()
						.map(it -> Criteria.where("activities").is(it))
						.collect(Collectors.toList());
			}
		}
}
