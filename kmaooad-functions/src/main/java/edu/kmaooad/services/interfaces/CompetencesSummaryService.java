package edu.kmaooad.services.interfaces;

import java.util.Map;

public interface CompetencesSummaryService {
    Map<String, Long> getCompetencesSummaryForStudentGroup(String groupId) throws Exception;
}
