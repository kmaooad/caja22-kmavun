package edu.kmaooad.repository;

import edu.kmaooad.models.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, String> {}
