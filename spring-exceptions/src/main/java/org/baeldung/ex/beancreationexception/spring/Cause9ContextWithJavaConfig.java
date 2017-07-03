package org.baeldung.ex.beancreationexception.spring;

import org.baeldung.ex.beancreationexception.cause9.BeanB;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("org.baeldung.ex.beancreationexception.cause9")
@ImportResource("classpath:beancreationexception_cause9.xml")
public class Cause9ContextWithJavaConfig {
    @Autowired
    private
    BeanFactory beanFactory;

    public Cause9ContextWithJavaConfig() {
        super();
    }

    // beans

    @Bean
    public BeanB beanB() {
        beanFactory.getBean("beanA");
        return new BeanB();
    }

}