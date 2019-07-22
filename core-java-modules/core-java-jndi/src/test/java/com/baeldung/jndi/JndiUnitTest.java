package com.baeldung.jndi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JndiUnitTest {

    private static InitialContext ctx;
    private static DriverManagerDataSource ds;

    @BeforeAll
    public static void setUp() throws NamingException {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        ds = new DriverManagerDataSource("jdbc:h2:mem:mydb");
        builder.activate();

        JndiTemplate jndiTemplate = new JndiTemplate();
        ctx = (InitialContext) jndiTemplate.getContext();
    }

    @Test
    public void testNameCreation() throws InvalidNameException {
        Name objectName = new CompositeName("java:comp/env/jdbc");
        objectName.add("example");

        assertEquals("env", objectName.get(1));
        assertEquals("example", objectName.get(objectName.size() - 1));
    }

    @Test
    public void testBindObject() throws NamingException {
       ds.setDriverClassName("org.h2.Driver");
       ctx.bind("java:comp/env/jdbc/datasource", ds);
    }

    @Test
    public void testLookupObject() throws NamingException, SQLException {
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");

        assertNotNull(ds);
        assertNotNull(ds.getConnection());
    }

}
