package com.example.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.swing.*;

/*
* PURE JAVA BASED CONFIG FOR SPRING
* @Configuration - to indicate this is the configuration file.
* @ComponentScan - same as component-scan tag in xml file with package as the arg.
* @PropertySource - to link the property file defined.
*/

@Configuration
@ComponentScan("com.example.springdemo")
@PropertySource("classpath:sports.properties")
public class SportConfig {

    /*
     * Define bean for SadFortuneService, using @Bean. No need to use annotations in
     * bean classes anymore. The method name is the actual bean ID.
     * Returns the implementation to ref when using the given method.
    */
    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }

    /*
    * Define bean for SwimCoach and inject the dependency.
    * Inject dependecy by calling the dependency bean defined above.
    * */
    @Bean
    public Coach swimCoach() {
        return new SwimCoach(sadFortuneService());
    }

}
