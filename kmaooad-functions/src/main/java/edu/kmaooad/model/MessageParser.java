package edu.kmaooad.model;

import org.json.JSONObject;

public class MessageParser {

    public static Message parseJsonString(String bodyJson) throws org.json.JSONException {
        JSONObject obj = new JSONObject(bodyJson);
        Long messageId = obj.getJSONObject("message").getLong("message_id");
        String text = obj.getJSONObject("message").getString("text");
        return new Message(messageId,text);
    }
}
