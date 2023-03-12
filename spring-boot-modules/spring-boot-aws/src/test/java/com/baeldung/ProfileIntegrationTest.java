package com.baeldung;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.baeldung.aws.handler.LambdaHandler;

@SpringBootApplication
public class ProfileIntegrationTest {

    MockLambdaContext lambdaContext = new MockLambdaContext();

    @Test
    void whenTheUsersPathIsInvokedViaLambda_thenShouldReturnAList() throws IOException {
        System.out.println("Hey Spring is not started yet");
        LambdaHandler lambdaHandler = new LambdaHandler();
        System.out.println("Voila, Spring now is started");
        AwsProxyRequest req = new AwsProxyRequestBuilder("/api/v1/users", "GET").build();
        AwsProxyResponse resp = lambdaHandler.handleRequest(req, lambdaContext);
        System.out.println(resp.getBody());
        Assertions.assertNotNull(resp.getBody());
        Assertions.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    void whenWrongPathPathIsInvokedViaLambda_thenShouldNotFound() throws IOException {
        System.out.println("Hey Spring is not started yet");
        LambdaHandler lambdaHandler = new LambdaHandler();
        System.out.println("Voila, Spring now is started");
        AwsProxyRequest req = new AwsProxyRequestBuilder("/api/v1/users/plus-one-level", "GET").build();
        AwsProxyResponse resp = lambdaHandler.handleRequest(req, lambdaContext);
        Assertions.assertEquals(resp.getStatusCode(), 404);
    }
}
