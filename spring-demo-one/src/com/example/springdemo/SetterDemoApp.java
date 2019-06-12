package com.example.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // load Spring config
        ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        
        // retrive bean from Spring container
        CricketCoach coach = context.getBean("myCricketCoach", CricketCoach.class);
        
        // call methods on the bean
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());
        
        System.out.println(coach.getEmailAddress());
        System.out.println(coach.getTeam());
        // close the context
        context.close();
    }
    
}
