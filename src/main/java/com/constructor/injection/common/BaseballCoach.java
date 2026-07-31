package com.constructor.injection.common;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Concrete implementation of {@link Coach} for baseball-specific training routines.
 * <p>
 * This bean is marked with {@link Primary} to ensure it is selected as the default
 * candidate when multiple {@link Coach} implementations are present in the context.
 * It showcases modern Spring best practices for constructor injection, immutability,
 * externalized configuration, and lifecycle management.
 * </p>
 */
@Component
@Primary
public class BaseballCoach implements Coach {

    private static final Logger log = LoggerFactory.getLogger(BaseballCoach.class);

    private final String teamName;

    /**
     * Constructs a new BaseballCoach with a configurable team name.
     * <p>
     * Explicitly annotated with {@link Autowired} to emphasize the constructor-injection
     * pattern (optional in Spring 4.3+ if only one constructor exists, but kept for clarity).
     * </p>
     *
     * @param teamName the name of the baseball team, injected from application properties.
     *                 Defaults to "Default Dodgers" if the property {@code baseball.team.name}
     *                 is not defined in the environment.
     */
    @Autowired
    public BaseballCoach(@Value("${baseball.team.name:Default Dodgers}") String teamName) {
        this.teamName = Objects.requireNonNull(teamName, "Team name must not be null");
        log.info("🥎 Constructor Injection successful for BaseballCoach: team = '{}'", teamName);
    }

    /**
     * Lifecycle callback executed after all dependencies are injected and the bean is fully initialized.
     * Replaces the outdated {@code System.out.println} from the constructor to separate
     * instantiation logic from initialization logic.
     */
    @PostConstruct
    public void init() {
        log.info("⚾ BaseballCoach bean is fully initialized and ready for the season!");
    }

    @Override
    public String getDailyWorkout() {
        return String.format("Practice batting drills with %s for 20 minutes.", teamName);
    }

    /**
     * Expanded business logic providing a team-specific warm-up instruction.
     * <p>
     * This demonstrates how constructor-injected properties can be leveraged across
     * multiple public methods to enrich the domain behavior.
     * </p>
     *
     * @return a warm-up routine string customized for the current team.
     */
    public String getWarmUpRoutine() {
        return String.format("Start with dynamic stretching and agility drills for %s.", teamName);
    }
}
