package com.baeldung.httpclient;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.DefaultRedirectStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpClientRedirectLiveTest {

    @Test
    void givenRedirectsAreDisabled_whenConsumingUrlWhichRedirects_thenNotRedirected() throws IOException {

        final HttpGet request = new HttpGet("http://t.co/I5YYd9tddw");

        try (CloseableHttpClient httpClient = HttpClients.custom()
            .disableRedirectHandling()
            .build()) {
            httpClient.execute(request, response -> {
                assertThat(response.getCode(), equalTo(301));
                return response;
            });
        }
    }

    // redirect with POST

    @Test
    void givenPostRequest_whenConsumingUrlWhichRedirects_thenNotRedirected() throws IOException {

        final HttpPost request = new HttpPost("http://t.co/I5YYd9tddw");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
            .disableRedirectHandling()
            .build()) {
            httpClient.execute(request, response -> {
                assertThat(response.getCode(), equalTo(301));
                return response;
            });
        }
    }

    @Test
    void givenRedirectingPOST_whenConsumingUrlWhichRedirectsWithPOST_thenRedirected() throws IOException {

        final HttpPost request = new HttpPost("http://t.co/I5YYd9tddw");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setRedirectStrategy(new DefaultRedirectStrategy())
            .build()) {
            httpClient.execute(request, response -> {
                assertThat(response.getCode(), equalTo(200));
                return response;
            });
        }
    }

}
