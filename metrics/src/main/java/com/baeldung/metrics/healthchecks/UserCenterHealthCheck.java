package com.baeldung.metrics.healthchecks;

import com.codahale.metrics.health.HealthCheck;

class UserCenterHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
