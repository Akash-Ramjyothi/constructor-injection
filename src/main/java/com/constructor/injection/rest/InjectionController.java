package com.constructor.injection.rest;

import com.constructor.injection.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectionController {

    private Coach myCoach;

    @Autowired
    public InjectionController(@Qualifier("cricketCoach") Coach theCoach) {
        System.out.println("💉In Constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
