package com.constructor.injection.rest;

import com.constructor.injection.common.Coach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class InjectionController {

    private final Coach coach;

    public InjectionController(@Qualifier("aquaticCoach") Coach coach) {
        System.out.println("💉 Constructor Injection: " + getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/daily-workout")
    public Map<String, Object> getDailyWorkout() {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "workout", coach.getDailyWorkout(),
                "coachType", coach.getClass().getSimpleName()
        );
    }

    /**
     * Simple diagnostic endpoint
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "checkedAt", LocalDateTime.now(),
                "controller", getClass().getSimpleName()
        );
    }
}
