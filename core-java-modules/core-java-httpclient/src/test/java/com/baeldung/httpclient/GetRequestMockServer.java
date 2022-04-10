package com.baeldung.httpclient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpStatusCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class GetRequestMockServer {

    public static ClientAndServer mockServer;
    public static String serviceUrl;

    private static int serverPort;

    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final String PATH = "/test1";
    public static final String METHOD = "GET";

    @BeforeAll
    static void startServer() throws IOException, URISyntaxException {
        serverPort = getFreePort();
        serviceUrl = "http://" + SERVER_ADDRESS + ":" + serverPort + PATH;
        mockServer = startClientAndServer(serverPort);
        mockGetRequest();
    }

    @AfterAll
    static void stopServer() {
        mockServer.stop();
    }

    private static void mockGetRequest() {
        new MockServerClient(SERVER_ADDRESS, serverPort)
          .when(
            request()
              .withPath(PATH)
              .withMethod(METHOD),
            exactly(5)
          )
          .respond(
            response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withBody("{\"message\":\"ok\"}")
          );
    }

    private static int getFreePort () throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }

}
