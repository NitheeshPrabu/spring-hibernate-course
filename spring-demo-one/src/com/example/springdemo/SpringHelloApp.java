package com.example.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {
    
    public static void main(String[] args) {
        
        //load the spring config files
        ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        
        //retrieve bean from spring container
        Coach coach = context.getBean("myCoach", Coach.class);
        
        //call methods on the bean
        System.out.println(coach.getDailyWorkout());
        
        System.out.println(coach.getDailyFortune());
        
        //close the context
        context.close();
    }
}
