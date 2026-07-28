package com.constructor.injection.config;

import com.constructor.injection.common.Coach;
import com.constructor.injection.common.SwimCoach;
import com.constructor.injection.common.TennisCoach;
import com.constructor.injection.common.BaseballCoach;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Spring configuration for sport-related beans.
 * <p>
 * Defines coaches with different profiles, property-based tuning, and lifecycle hooks.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SportConfig.SwimProperties.class)
public class SportConfig {

    /**
     * Type-safe properties for swim coach configuration.
     * Binds to "coach.swim.*" prefix.
     */
    @Validated
    @ConfigurationProperties(prefix = "coach.swim")
    public static class SwimProperties {
        @NotBlank(message = "Swim level must not be blank")
        @Pattern(regexp = "BEGINNER|INTERMEDIATE|ADVANCED|EXPERT",
                 message = "Swim level must be one of: BEGINNER, INTERMEDIATE, ADVANCED, EXPERT")
        private String level = "BEGINNER";

        @Pattern(regexp = "FREESTYLE|BREASTSTROKE|BACKSTROKE|BUTTERFLY")
        private String stroke = "FREESTYLE";

        // getters and setters
        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
        public String getStroke() { return stroke; }
        public void setStroke(String stroke) { this.stroke = stroke; }
    }

    // ------------------------------------------------------------------------
    // Swim Coach – active in non-test environments (production, dev, etc.)
    // Uses type-safe properties and explicit lifecycle annotations.
    // ------------------------------------------------------------------------

    @Bean(name = "aquaticCoach")
    @Profile("!test")
    public Coach swimCoach(SwimProperties swimProps) {
        log.info("Creating SwimCoach with level='{}', stroke='{}'", 
                 swimProps.getLevel(), swimProps.getStroke());
        return new SwimCoach(swimProps.getLevel(), swimProps.getStroke());
    }

    // ------------------------------------------------------------------------
    // Swim Coach for test profile – uses a fixed, predictable configuration.
    // ------------------------------------------------------------------------

    @Bean(name = "testSwimCoach")
    @Profile("test")
    public Coach testSwimCoach() {
        log.info("Creating SwimCoach for TEST profile with default settings");
        return new SwimCoach("BEGINNER", "FREESTYLE");
    }

    // ------------------------------------------------------------------------
    // Tennis Coach – active only when property 'coach.tennis.enabled' is true.
    // Demonstrates conditional bean creation.
    // ------------------------------------------------------------------------

    @Bean
    @ConditionalOnProperty(name = "coach.tennis.enabled", havingValue = "true", matchIfMissing = false)
    public Coach tennisCoach() {
        log.info("Creating TennisCoach (enabled via property)");
        return new TennisCoach();
    }

    // ------------------------------------------------------------------------
    // Baseball Coach – active only in the "dev" profile.
    // ------------------------------------------------------------------------

    @Bean
    @Profile("dev")
    public Coach baseballCoach() {
        log.info("Creating BaseballCoach for DEV profile");
        return new BaseballCoach();
    }

    // ------------------------------------------------------------------------
    // Default (primary) coach – fallback when no profile-specific bean is requested.
    // Using the swim coach as primary.
    // ------------------------------------------------------------------------

    @Bean
    @Primary
    @Profile("!test") // avoid ambiguity in test
    public Coach primaryCoach(@Qualifier("aquaticCoach") Coach swimCoach) {
        log.info("Setting primary coach to the aquatic coach");
        return swimCoach;
    }

    // ------------------------------------------------------------------------
    // Lifecycle hooks can also be applied directly inside the bean class.
    // We demonstrate them here as well for clarity.
    // ------------------------------------------------------------------------

    @PostConstruct
    public void initConfig() {
        log.info("SportConfig fully initialized");
    }

    @PreDestroy
    public void destroyConfig() {
        log.info("SportConfig is being destroyed");
    }
}
