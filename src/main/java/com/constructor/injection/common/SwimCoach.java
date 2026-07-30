package com.constructor.injection.common;

import java.util.logging.Logger;

/**
 * Implementation of {@link Coach} for swimming activities.
 * Demonstrates constructor injection with a dependency on a fortune service.
 */
public class SwimCoach implements Coach {

    private static final Logger LOGGER = Logger.getLogger(SwimCoach.class.getName());

    // Dependency - injected via constructor
    private final FortuneService fortuneService;

    /**
     * Constructor for constructor injection.
     * @param fortuneService the service providing daily fortunes (must not be null)
     * @throws IllegalArgumentException if fortuneService is null
     */
    public SwimCoach(FortuneService fortuneService) {
        if (fortuneService == null) {
            throw new IllegalArgumentException("FortuneService cannot be null");
        }
        this.fortuneService = fortuneService;
        LOGGER.info("🤿 SwimCoach initialized with fortuneService: " + fortuneService.getClass().getSimpleName());
        // Keep the original print for demonstration
        System.out.println("🤿 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 metres as warm-up, then 5x200m intervals with 30s rest.";
    }

    /**
     * Returns a daily fortune by delegating to the injected service.
     * @return a fortune string
     */
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    // Optional: override toString for better logging
    @Override
    public String toString() {
        return "SwimCoach{" +
                "fortuneService=" + fortuneService.getClass().getSimpleName() +
                '}';
    }
}
