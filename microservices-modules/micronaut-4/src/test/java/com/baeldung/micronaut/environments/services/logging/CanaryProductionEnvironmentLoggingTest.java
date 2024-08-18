package com.baeldung.micronaut.environments.services.logging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(environments = { "canary-production" })
public class CanaryProductionEnvironmentLoggingTest {

    @Inject
    LoggingService loggingService;

    @Test
    public void sendEvent_whenEnvIsCanaryProduction_overridesDefault_andUsesFileLoggingService() {
        String prodLog = loggingService.log("something");

        assertThat(prodLog).isEqualTo("logging to some file: [something]");
    }
}
