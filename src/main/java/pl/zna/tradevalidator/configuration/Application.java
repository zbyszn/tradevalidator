package pl.zna.tradevalidator.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Created by zbyszek on 13.06.2017.
 */
@Configuration
@SpringBootApplication
@IntegrationComponentScan
@EnableAutoConfiguration
@ComponentScan("pl.zna.tradevalidator")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
     }

}