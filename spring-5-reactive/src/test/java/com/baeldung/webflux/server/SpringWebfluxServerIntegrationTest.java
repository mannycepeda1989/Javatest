package com.baeldung.webflux.server;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpringWebfluxServerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenTheTimeEndpointIsCalled_thenWeGetStatus200() {
        this.webTestClient.get().uri("/time").exchange().expectStatus().isOk();
    }

    @Test
    public void whenTheTimeEndpointIsCalledAndBufferedForOneSecond_thenWeGetTwoValues() {
        // when:
        List<Long> result = this.webTestClient.get().uri("/time").exchange().returnResult(Long.class).getResponseBody()
                .buffer(Duration.ofSeconds(1)).blockFirst();

        // then:
        Assert.assertNotNull("Response is null", result);

        // and:
        Assert.assertEquals("Response has not the expected number of entries", 2, result.size());
    }
}
