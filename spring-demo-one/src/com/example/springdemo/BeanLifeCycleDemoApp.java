package com.example.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // load the Spring config file
        ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
        
        // retrieve bean from Spring container
        Coach coach = context.getBean("myCoach", Coach.class);
        
        // call methods on the bean
        System.out.println(coach.getDailyWorkout());
        
        // close the context
        context.close();
    }
    
}
