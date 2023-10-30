package com.intervew.jobservice.job;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Job<T> {
    CompletableFuture<T> run();

    JobStatus getStatus();

    UUID getJobId();
}
