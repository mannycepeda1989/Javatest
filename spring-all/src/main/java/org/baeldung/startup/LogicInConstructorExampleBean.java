package org.baeldung.startup;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
class LogicInConstructorExampleBean {

    private static final Logger LOG = LoggerFactory.getLogger(LogicInConstructorExampleBean.class);

    @Autowired
    public LogicInConstructorExampleBean(Environment environment) {
        LOG.info("Env Default Profiles", Arrays.asList(environment.getDefaultProfiles()));
    }
}
