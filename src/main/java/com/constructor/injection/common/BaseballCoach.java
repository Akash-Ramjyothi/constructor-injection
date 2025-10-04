package com.constructor.injection.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice Baseball Batting for 20 minutes.";
    }
}
