package edu.kmaooad.repository;

import org.bson.Document;

import java.util.HashMap;

public interface RequestRepository {
    void addRequest(Document doc);
}
