package com.example.springdemo;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {

    private String[] fortunes = {
            "Today is your lucky day!",
            "Beware of the wolf in sheep's clothing",
            "Diligence is the mother of good luck",
            "The journey is the reward"
    };

    private Random random = new Random();

    @Override
    public String getFortune() {
        int index = random.nextInt(fortunes.length);
        return fortunes[index];
    }
}
