package com.example.springdemo;

public class SadFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "You will be sad today :(";
    }
}
