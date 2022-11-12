package edu.kmaooad;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import edu.kmaooad.model.Response;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;


import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TelegramWebhook extends FunctionInvoker<String, Response> {
    /**
     * This function listens at endpoint "/api/TelegramWebhook". To invoke it using "curl" command in bash:
     * curl -d "HTTP Body" {your host}/api/TelegramWebhook
     */

    @FunctionName("TelegramWebhook")
    protected HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION)
                    HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        final String body = request.getBody().orElse(null);
        Response response = handleRequest(body,context);
        HttpStatus status = response.isOk ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return request.createResponseBuilder(status)
                .body(response.getResponse())
                .build();
    }
}
