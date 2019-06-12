package com.example.springdemo;

public class TrackCoach implements Coach {
    
    private FortuneService fortuneService;
    
    public TrackCoach() { }
    
    // for constructor injection
    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
    
    // add an init-method
    public void customInit() {
        System.out.println("TrackCoach: inside method customInit");
    }
    
    // add a destory method
    public void customDestroy() {
        System.out.println("TrackCoach: inside method customDestroy");
    }
}
