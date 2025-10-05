package com.constructor.injection.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("▶️ Do my Startup Stuff: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("🧹 Do my Cleanup Stuff: " + getClass().getSimpleName());
    }

    public CricketCoach() {
        System.out.println("🏏 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practise fat bowling for 15 minutes :-)";
    }
}
