package com.constructor.injection.common;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Implementation of {@link Coach} for cricket training.
 * Demonstrates constructor injection with a fortune service and
 * uses thread-safe counters for session tracking.
 */
@Component
public class CricketCoach implements Coach {

    private static final Logger LOGGER = Logger.getLogger(CricketCoach.class.getName());

    private final FortuneService fortuneService;
    private final Random random = new Random();
    private final AtomicInteger sessionsCompleted = new AtomicInteger(0);

    /**
     * Constructor for dependency injection.
     * @param fortuneService service providing daily fortunes (must not be null)
     * @throws IllegalArgumentException if fortuneService is null
     */
    public CricketCoach(FortuneService fortuneService) {
        if (fortuneService == null) {
            throw new IllegalArgumentException("FortuneService must not be null");
        }
        this.fortuneService = fortuneService;
        LOGGER.info("🏏 CricketCoach initialized with fortuneService: " +
                fortuneService.getClass().getSimpleName());
        System.out.println("🏏 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        int sessionNumber = sessionsCompleted.incrementAndGet();
        String drill = generateRandomDrill();
        int intensity = calculateIntensityScore(sessionNumber);
        String fortune = fortuneService.getFortune();

        LOGGER.fine("Generated workout for session " + sessionNumber);
        return String.format(
                "Session %d: %s | Intensity Score: %d | Fortune: %s",
                sessionNumber, drill, intensity, fortune
        );
    }

    /**
     * Provides an additional service: returns today's fortune.
     * @return a fortune string from the injected service
     */
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    /**
     * Generates a realistic cricket drill.
     * Could be extended to use an injected drill provider.
     */
    private String generateRandomDrill() {
        String[] drills = {
                "Practise fast bowling for 15 minutes",
                "Work on yorkers and line-length accuracy",
                "Focus on swing bowling with the new ball",
                "Bowl 6 overs targeting outside off-stump",
                "Improve death-over variations"
        };
        return drills[random.nextInt(drills.length)];
    }

    /**
     * Calculates intensity score based on session number and randomness.
     */
    private int calculateIntensityScore(int sessionNumber) {
        return Math.min(100, 60 + sessionNumber * 5 + random.nextInt(10));
    }

    /**
     * Resets session counter (useful for testing).
     */
    public void resetSessions() {
        sessionsCompleted.set(0);
        LOGGER.info("Sessions reset to 0");
    }
}
