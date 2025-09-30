package com.constructor.injection;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practise fat bowling for 15 minutes.!!!!";
    }
}
