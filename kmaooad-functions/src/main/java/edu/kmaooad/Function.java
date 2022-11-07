package edu.kmaooad;

import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.*;


import java.util.HashMap;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/TelegramWebhook". To invoke it using "curl" command in bash:
     * curl -d "HTTP Body" {your host}/api/TelegramWebhook
     */
    @FunctionName("TelegramWebhook")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION)
                    HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");


        final String bodyJson = request.getBody().orElse(null);
        Long messageId;
        try {
            ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:0FuzYyhgLsJU6nS9@cluster0.1tup9.mongodb.net/?retryWrites=true&w=majority");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("requests");

            JSONObject obj = new JSONObject(bodyJson);
            messageId = obj.getJSONObject("message").getLong("message_id");
            HashMap<String, Object> yourHashMap = new Gson().fromJson(obj.toString(), HashMap.class);
            collection.insertOne(new Document(yourHashMap));
        } catch (JSONException jse) {
            return request.createResponseBuilder(HttpStatus.OK).body("Body without JSON").build();
        } catch (NullPointerException npe) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("This message does not contain body").build();
        }

        return request.createResponseBuilder(HttpStatus.OK).body(messageId).build();

    }
}
