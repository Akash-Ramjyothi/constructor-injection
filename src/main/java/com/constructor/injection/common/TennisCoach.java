package com.constructor.injection.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private final FortuneService fortuneService;

    // Constructor injection – @Autowired is optional in Spring 4.3+ for single‑constructor beans
    @Autowired
    public TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
        System.out.println("🏓 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice Tennis for 10 minutes.";
    }

    // New method that uses the injected dependency
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
