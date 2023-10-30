package com.intervew.jobservice.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    private final JobProperties jobProperties;

    public ThreadPoolConfig(final JobProperties jobProperties) {
        this.jobProperties = jobProperties;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(jobProperties.getCorePoolSize());
        executor.setMaxPoolSize(jobProperties.getMaxPoolSize());
        executor.setQueueCapacity(jobProperties.getQueueCapacity());
        executor.setThreadNamePrefix("job-executor-");
        executor.initialize();
        return executor;
    }
}
