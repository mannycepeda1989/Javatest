package com.baeldung.shutdown;

import com.baeldung.autoconfiguration.MySQLAutoconfiguration;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MySQLAutoconfiguration.class)
public class Application {

    public static void main(String [] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).web(false).run();
        System.out.println("Spring Boot application started");
        ctx.getBean(TerminateBean.class);
        ctx.close();
//        SpringApplication.exit(ctx, () -> 0);
        System.out.println("Exit Spring Boot");

//        int exitCode = SpringApplication.exit(ctx, (ExitCodeGenerator) () -> {
//            // no errors
//            return 0;
//        });
//        System.exit(exitCode);
    }
}
