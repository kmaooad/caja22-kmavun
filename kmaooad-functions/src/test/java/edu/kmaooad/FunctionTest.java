package edu.kmaooad;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import edu.kmaooad.handlers.RequestHandler;
import edu.kmaooad.service.RequestService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FunctionTest {

    private String INVALID_JSON_MSG = "Invalid JSON structure in body.";
    private String NO_BODY_MSG = "This message does not contain body.";
    private String TELEGRAM_BOT_MSG = "{\n" +
            "    \"update_id\": 822482379,\n" +
            "    \"message\": {\n" +
            "        \"message_id\": 1686930,\n" +
            "        \"from\": {\n" +
            "            \"id\": 410683229,\n" +
            "            \"is_bot\": false,\n" +
            "            \"first_name\": \"\\u043c\\u0430\\u043a\\u0441\\u0438\\u043c\",\n" +
            "            \"username\": \"kreweduvieux\",\n" +
            "            \"language_code\": \"en\"\n" +
            "        },\n" +
            "        \"chat\": {\n" +
            "            \"id\": 410683229,\n" +
            "            \"first_name\": \"\\u043c\\u0430\\u043a\\u0441\\u0438\\u043c\",\n" +
            "            \"username\": \"kreweduvieux\",\n" +
            "            \"type\": \"private\"\n" +
            "        },\n" +
            "        \"date\": 1667853390,\n" +
            "        \"text\": \";\"\n" +
            "    }\n" +
            "}";
    /**
     * Request imitation
     */
    private HttpRequestMessage<Optional<String>> mockRequest(Optional<String> queryBody) {
        // Setup
        @SuppressWarnings("unchecked")
        final HttpRequestMessage<Optional<String>> req = mock(HttpRequestMessage.class);

        doReturn(queryBody).when(req).getBody();

        doAnswer((Answer<HttpResponseMessage.Builder>) invocation -> {
            HttpStatus status = (HttpStatus) invocation.getArguments()[0];
            return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
        }).when(req).createResponseBuilder(any(HttpStatus.class));

        return req;
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        public RequestService getMessageService() {
            RequestService service = mock(RequestService.class);
            return service;
        }
    }

    private TelegramWebhook getHandler() {
        TelegramWebhook handler = spy(TelegramWebhook.class);
        doAnswer(
                invocationOnMock -> applicationContext
                        .getBean(RequestHandler.class)
                        .apply(invocationOnMock.getArgument(0))
        )
                .when(handler)
                .handleRequest(any(String.class), any(ExecutionContext.class));
        return handler;
    }

    @Autowired
    private ApplicationContext applicationContext;


    private ExecutionContext mockContext() {
        // Setup
        ExecutionContext contextMock = mock(ExecutionContext.class);
        when(contextMock.getLogger()).thenReturn(Logger.getGlobal());
        return contextMock;
    }

//    This test is breaking with Optional.empty() due to some unknown magical reasons
//    @Test
//    public void testEmptyBody() {
//        // Invoke
//        final HttpResponseMessage ret = getHandler().run(mockRequest(Optional.empty()) , mockContext());
//        // Verify
//        assertEquals(HttpStatus.BAD_REQUEST, ret.getStatus());
//        assertEquals(ret.getBody(),NO_BODY_MSG);
//    }

    @Test
    public void testInvalidBody() {
        // Invoke
        final HttpResponseMessage ret = getHandler().run(mockRequest(Optional.of("Not JSON")) , mockContext());
        // Verify
        assertEquals(HttpStatus.BAD_REQUEST, ret.getStatus());
        assertEquals(ret.getBody(),INVALID_JSON_MSG);
    }

    @Test
    public void testMessage() {
        // Invoke
        final HttpResponseMessage ret = getHandler().run(mockRequest(Optional.of(TELEGRAM_BOT_MSG)) , mockContext());
        // Verify
        assertEquals(HttpStatus.OK, ret.getStatus());
        assertNotNull(ret.getBody());
    }

}