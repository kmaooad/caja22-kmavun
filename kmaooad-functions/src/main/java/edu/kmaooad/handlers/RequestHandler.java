package edu.kmaooad.handlers;


import edu.kmaooad.models.Message;
import edu.kmaooad.utils.MessageParser;
import edu.kmaooad.models.Response;
import edu.kmaooad.services.RequestService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class RequestHandler implements Function<Optional<String>, Response> {

    @Autowired
    private RequestService reqServ;

    @Override
    public Response apply(Optional<String> request) {
        final String body = request.orElse(null);
        Message msg;
        try {
            msg = MessageParser.parseJsonString(body);
        } catch (JSONException jse) {
            return new Response("Invalid JSON structure in body.");
        } catch (NullPointerException npe) {
            return new Response("This message does not contain body.");
        }
        reqServ.saveMessage(msg);
        return new Response(msg.getMessageId());
    }
}
