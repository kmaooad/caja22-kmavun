package edu.kmaooad;

import com.microsoft.azure.functions.*;
import net.bytebuddy.dynamic.DynamicType;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


/**
 * Unit test for Function class.
 */
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

    private ExecutionContext mockContext() {
        // Setup
        final ExecutionContext context = mock(ExecutionContext.class);
        doReturn(Logger.getGlobal()).when(context).getLogger();
        return context;
    }

    @Test
    public void testEmptyBody() {
        // Invoke
        final HttpResponseMessage ret = new Function().run(mockRequest(Optional.empty()) , mockContext());
        // Verify
        assertEquals(HttpStatus.BAD_REQUEST, ret.getStatus());
        assertEquals(ret.getBody(),NO_BODY_MSG);
    }

    @Test
    public void testInvalidBody() {
        // Invoke
        final HttpResponseMessage ret = new Function().run(mockRequest(Optional.of("Not JSON")) , mockContext());
        // Verify
        assertEquals(HttpStatus.BAD_REQUEST, ret.getStatus());
        assertEquals(ret.getBody(),INVALID_JSON_MSG);
    }

    @Test
    public void testMessage() {
        // Invoke
        final HttpResponseMessage ret = new Function().run(mockRequest(Optional.of(TELEGRAM_BOT_MSG)) , mockContext());
        // Verify
        assertEquals(HttpStatus.OK, ret.getStatus());
        assertNotNull(ret.getBody());
    }


}
