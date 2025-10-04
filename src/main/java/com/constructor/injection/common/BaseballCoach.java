package com.constructor.injection.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BaseballCoach implements Coach {

    public BaseballCoach() {
        System.out.println("🥎 In Constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice Baseball Batting for 20 minutes.";
    }
}
