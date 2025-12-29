package com.constructor.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class InjectionApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        printStartupBanner(args);

        ConfigurableApplicationContext context =
                SpringApplication.run(InjectionApplication.class, args);

        long startupDuration = System.currentTimeMillis() - startTime;
        logStartupInfo(context.getEnvironment(), context.getBeanDefinitionCount(), startupDuration);

        registerShutdownHook();
    }

    /**
     * Prints startup banner and arguments
     */
    private static void printStartupBanner(String[] args) {
        System.out.println("=========================================");
        System.out.println(" 🧩 Constructor Injection Application ");
        System.out.println(" Started at : " + LocalDateTime.now());
        System.out.println(" Arguments  : " + Arrays.toString(args));
        System.out.println("=========================================");
    }

    /**
     * Logs startup diagnostics
     */
    private static void logStartupInfo(
            Environment environment,
            int beanCount,
            long startupDuration
    ) {
        String[] activeProfiles = environment.getActiveProfiles();
        String profiles = activeProfiles.length == 0
                ? "default"
                : String.join(", ", activeProfiles);

        System.out.printf(
                "✅ App started | StartupTime=%dms | ActiveProfiles=%s | BeansLoaded=%d%n",
                startupDuration,
                profiles,
                beanCount
        );
    }

    /**
     * Graceful shutdown logging
     */
    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println(
                        "🛑 Application shutting down gracefully at " + LocalDateTime.now()
                )
        ));
    }
}
