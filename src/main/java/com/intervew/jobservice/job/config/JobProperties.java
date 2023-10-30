package com.intervew.jobservice.job.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "job")
@Data
public class JobProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
}
