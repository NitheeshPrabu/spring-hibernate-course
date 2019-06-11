package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
 * @Component("myCoach") - to give a custom ID
 * @Component - makes bean ID = tennisCoach - camel case
 *
 * @Scope("type") - to define the scope of the bean objects
 * type - singleton, prototype
 * For prototype scoped beans, @PreDestroy is not called by Spring. Client code has to do manual cleanup.
 */

@Component
@Scope("singleton")
public class TennisCoach implements Coach {

    /*
     * FIELD INJECTION
     * Put @Autowired here to automatically inject Service Implementation
     * behind the scenes using Java Reflection
     *
     * @Qualifier helps resolve which unique implementation to inject
     */
    @Autowired
    @Qualifier("randomFortuneService")
    private FortuneService fortuneService;

    /*
    * CONSTRUCTOR INJECTION
    * Spring automatically searches for a class that implements a FortuneService interface.
    @Autowired
    public TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
    */

    /*
    * SETTER INJECTION
    * You can actually give any name to the method. Need not start with set.
    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
    */

    /*
    * INIT and DESTROY LifeCycle methods
    * @PostConstruct - init-method arg in xml config. Called after constructing object.
    * @PreDestroy - destroy-method arg in xml config. Called before destructing object.
    *
    * Access modifier
    *   The method can have any access modifier (public, protected, private)
    *
    * Return type
    *   The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.
    *
    * Method name
    *   The method can have any method name.
    *
    * Arguments
    *   The method can not accept any arguments. The method should be no-arg.
    */

    @PostConstruct
    public void customInit() {
        System.out.println("TennisCoach: inside customInit method");
    }

    @PreDestroy
    public void customDestroy() {
        System.out.println("TennisCoach: inside customDestroy method");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
