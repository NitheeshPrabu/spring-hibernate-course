package com.example.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // load the Spring config file
        ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        
        // retrieve bean from Spring container
        Coach coach = context.getBean("myCoach", Coach.class);
        Coach alphaCoach = context.getBean("myCoach", Coach.class);
        
        // check if objects are same
        boolean result = (coach == alphaCoach);
        System.out.println("\nPointing to same location? " + result);
        System.out.println("\nMemory location for coach: " + coach);
        System.out.println("\nMemory location for alphaCoach: " + alphaCoach);
        
        // close the context
        context.close();
    }
    
}
