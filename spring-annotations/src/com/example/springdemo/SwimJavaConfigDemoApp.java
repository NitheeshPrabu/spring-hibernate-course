package com.example.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

    public static void main(String[] args) {

        // read Spring config Java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyLoggerConfig.class, SportConfig.class);

        // get the bean from Spring container
        SwimCoach coach = context.getBean("swimCoach", SwimCoach.class);

        //call a method on the bean
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());

        System.out.println(coach.getEmail());
        System.out.println(coach.getTeam());

        //close the context
        context.close();
    }
}
