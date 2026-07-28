package com.constructor.injection.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Implementation of {@link Coach} for track running activities.
 * <p>
 * Supports customizable workout messages via the property {@code coach.track.workout}.
 */
@Slf4j
@Component
@Validated
public class TrackCoach implements Coach {

    private final FortuneService fortuneService;  // example dependency

    @NotBlank(message = "Workout message must not be blank")
    @Value("${coach.track.workout:Run for 5 minutes.}")
    private String workoutMessage;

    // ------------------------------------------------------------------------
    // Constructor injection – the recommended way for required dependencies.
    // ------------------------------------------------------------------------
    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
        log.info("TrackCoach instantiated with fortuneService: {}", 
                 fortuneService != null ? fortuneService.getClass().getSimpleName() : "null");
    }

    // ------------------------------------------------------------------------
    // Lifecycle hooks
    // ------------------------------------------------------------------------
    @PostConstruct
    public void init() {
        log.info("TrackCoach initialized with workoutMessage: '{}'", workoutMessage);
        // Any post‑construction setup (e.g., validate external resources)
    }

    @PreDestroy
    public void cleanup() {
        log.info("TrackCoach cleaning up resources...");
        // Release resources if needed
    }

    // ------------------------------------------------------------------------
    // Business method
    // ------------------------------------------------------------------------
    @Override
    public String getDailyWorkout() {
        log.debug("Returning daily workout: {}", workoutMessage);
        return workoutMessage;
    }

    // ------------------------------------------------------------------------
    // Additional method to show dependency usage (optional)
    // ------------------------------------------------------------------------
    public String getDailyFortune() {
        return fortuneService != null ? fortuneService.getFortune() : "No fortune today.";
    }

    // ------------------------------------------------------------------------
    // Override for better debugging
    // ------------------------------------------------------------------------
    @Override
    public String toString() {
        return String.format("TrackCoach{workoutMessage='%s'}", workoutMessage);
    }

    // Optionally override equals/hashCode if needed for collections
}
