package edu.kmaooad.utils;

import edu.kmaooad.model.Message;
import lombok.experimental.UtilityClass;
import org.json.JSONObject;

@UtilityClass
public class MessageParser {

    public static Message parseJsonString(String bodyJson) throws org.json.JSONException {
        JSONObject obj = new JSONObject(bodyJson);
        Long messageId = obj.getJSONObject("message").getLong("message_id");
        String text = obj.getJSONObject("message").getString("text");
        return new Message(messageId,text);
    }
}
