package com.constructor.injection.common;

/**
 * Defines the core contract for any sports coach within the application.
 * <p>
 * Implementations of this interface are expected to be Spring-managed beans
 * (typically annotated with {@link org.springframework.stereotype.Component})
 * that encapsulate sport-specific training logic, motivational strategies, and
 * practice scheduling.
 * </p>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * @Component
 * public class CricketCoach implements Coach {
 *     \@Override
 *     public String getDailyWorkout() {
 *         return "Practice bowling for 30 minutes.";
 *     }
 *
 *     \@Override
 *     public String getSportType() {
 *         return "Cricket";
 *     }
 * }
 * }</pre>
 *
 * @author Your Team
 * @version 2.0
 * @see BaseballCoach
 * @see CricketCoach
 */
public interface Coach {

    /**
     * Provides the primary daily workout plan for the athlete.
     * <p>
     * This is the core method that every coach must implement to define
     * the specific training routine for their respective sport.
     * </p>
     *
     * @return a non-null, descriptive string outlining the daily workout routine.
     */
    String getDailyWorkout();

    /**
     * Retrieves the specific type of sport this coach represents.
     * <p>
     * This method enforces that every concrete implementation clearly identifies
     * its sporting discipline, which is useful for runtime type discrimination,
     * logging, and dynamic UI rendering.
     * </p>
     *
     * @return the sport type (e.g., "Baseball", "Cricket", "Soccer", "Swimming").
     */
    String getSportType();

    /**
     * Provides a generic motivational message to boost athlete morale before training.
     * <p>
     * Coaches may override this method to provide sport-specific or team-specific
     * pep talks, but a sensible generic default is provided out-of-the-box to
     * avoid boilerplate in simple implementations.
     * </p>
     *
     * @return a motivational string, defaulting to a universally applicable phrase.
     */
    default String getMotivationalMessage() {
        return "Keep pushing your limits! Consistency is the mother of mastery.";
    }

    /**
     * Retrieves the default recommended practice duration for this sport.
     * <p>
     * Provides a standard baseline (60 minutes) that can be overridden by
     * sports requiring longer or shorter training sessions.
     * </p>
     *
     * @return the recommended duration in minutes.
     */
    default int getDefaultPracticeDuration() {
        return 60;
    }

    /**
     * Static utility method to perform a basic validation check on a coach instance.
     * <p>
     * Centralizes common validation logic (e.g., null checks and empty sport types)
     * to prevent repetitive code across service layers.
     * </p>
     *
     * @param coach the coach instance to validate (can be null).
     * @return {@code true} if the coach is not null and its {@code getSportType()}
     *         returns a non-null, non-blank value; {@code false} otherwise.
     */
    static boolean isValidCoach(Coach coach) {
        return coach != null
                && coach.getSportType() != null
                && !coach.getSportType().isBlank();
    }
}
