package com.constructor.injection.common;

public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("🤿 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 metres as warm-up.";
    }
}
