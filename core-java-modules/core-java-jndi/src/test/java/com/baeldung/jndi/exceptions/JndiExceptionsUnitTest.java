package com.baeldung.jndi.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NoInitialContextException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.annotation.DirtiesContext;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Isolated
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JndiExceptionsUnitTest {

    InitialContext ctx = null;

    SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();

    @Test
    @Order(1)
    void givenNoContext_whenLookupObject_thenThrowNoInitialContext() {
        assertThrows(NoInitialContextException.class, () -> {
            builder.deactivate();
            ctx = null;

            JndiTemplate jndiTemplate = new JndiTemplate();
            InitialContext ctx = (InitialContext) jndiTemplate.getContext();
            ctx.lookup("java:comp/env/jdbc/datasource");
            ctx.close();
        }).printStackTrace();
    }

    @Test
    @Order(2)
    void givenEmptyContext_whenLookupNotBounds_thenThrowNameNotFound() {
        assertThrows(NameNotFoundException.class, () -> {
            //            SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
            builder.activate();

            JndiTemplate jndiTemplate = new JndiTemplate();
            ctx = (InitialContext) jndiTemplate.getContext();
            ctx.lookup("badJndiName");
            ctx.close();
        }).printStackTrace();
    }

}