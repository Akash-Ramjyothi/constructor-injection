package com.constructor.injection.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CricketCoach implements Coach {

    private final Random random = new Random();
    private int sessionsCompleted = 0;

    public CricketCoach() {
        System.out.println("🏏 In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        sessionsCompleted++;

        return String.format(
                "Session %d: %s | Intensity Score: %d",
                sessionsCompleted,
                generateRandomDrill(),
                calculateIntensityScore()
        );
    }

    /**
     * Generates a realistic random cricket drill
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
     * Simulates a performance metric based on sessions
     */
    private int calculateIntensityScore() {
        return Math.min(100, 60 + sessionsCompleted * 5 + random.nextInt(10));
    }
}
