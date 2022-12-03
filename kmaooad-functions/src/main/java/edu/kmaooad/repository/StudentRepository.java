package edu.kmaooad.repository;

import edu.kmaooad.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>, StudentCriteriaRepository {
    Optional<Student> getById(String id);

    Optional<Student> getByEmail(String email);

    List<Student> findByDep(String dep);
}
