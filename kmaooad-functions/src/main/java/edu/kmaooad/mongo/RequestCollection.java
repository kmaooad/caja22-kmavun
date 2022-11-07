package edu.kmaooad.mongo;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.kmaooad.repository.RequestRepository;
import org.bson.Document;

import java.util.HashMap;

public class RequestCollection implements RequestRepository {

    private MongoCollection<Document> collection;

    public RequestCollection() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:0FuzYyhgLsJU6nS9@cluster0.1tup9.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");
        collection = database.getCollection("requests");
    }

    public void addRequest(Document doc){
        collection.insertOne(doc);
    }


}
