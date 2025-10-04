package com.constructor.injection.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice Tennis for 10 minutes.";
    }
}
