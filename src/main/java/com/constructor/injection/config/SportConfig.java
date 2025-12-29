package com.constructor.injection.config;

import com.constructor.injection.common.Coach;
import com.constructor.injection.common.SwimCoach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SportConfig {

    @Value("${coach.swim.level:BEGINNER}")
    private String swimLevel;

    /**
     * Swim coach bean active only in non-test environments
     */
    @Bean(
            name = "aquaticCoach",
            initMethod = "init",
            destroyMethod = "cleanup"
    )
    @Profile("!test")
    public Coach swimCoach() {
        return new SwimCoach(swimLevel);
    }
}
