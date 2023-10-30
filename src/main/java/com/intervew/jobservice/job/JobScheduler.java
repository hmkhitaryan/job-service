package com.intervew.jobservice.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class JobScheduler {

    private final List<Job<?>> jobs;

    private final ThreadPoolTaskExecutor taskExecutor;

    public JobScheduler(List<Job<?>> jobs, ThreadPoolTaskExecutor taskExecutor) {
        this.jobs = jobs;
        this.taskExecutor = taskExecutor;
    }


    @Scheduled(fixedRate = 3600000) // 1 hour
    public void scheduleHourlyJobs() {
        runJobs();
    }

    @Scheduled(fixedRate = 7200000) // 2 hours
    public void scheduleTwoHourlyJobs() {
        runJobs();
    }

    @Scheduled(fixedRate = 21600000) // 6 hours
    public void scheduleSixHourlyJobs() {
        runJobs();
    }

    @Scheduled(fixedRate = 43200000) // 12 hours
    public void scheduleTwelveHourlyJobs() {
        runJobs();
    }

    public void runAllJobsImmediately() {
        runJobs();
    }

    private void runJobs() {
        for (Job<?> job : jobs) {
            taskExecutor.execute(() -> {
                try {
                    job.run().join();
                } catch (Exception e) {
                    log.error("Error occurred when running job: " + job);
                }
            });
        }
    }
}
