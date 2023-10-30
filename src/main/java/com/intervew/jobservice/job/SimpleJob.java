package com.intervew.jobservice.job;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SimpleJob implements Job<String> {

    private JobStatus status;

    private UUID jobId;

    @Override
    public CompletableFuture<String> run() {
        return CompletableFuture.supplyAsync(() -> {
            // Your job logic here
            return "Job completed successfully";
        });
    }

    @Override
    public JobStatus getStatus() {
        return status;
    }

    @Override
    public UUID getJobId() {
        return jobId;
    }
}
