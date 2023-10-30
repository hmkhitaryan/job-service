package com.intervew.jobservice.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JobCancellationService {

    private final Map<Long, CompletableFuture<?>> jobFutures = new ConcurrentHashMap<>();

    public void requestCancellation(final long jobId) {
        final CompletableFuture<?> future = jobFutures.get(jobId);
        if (future != null) {
            future.cancel(true);
        }
    }

    public void registerJob(final long jobId, final CompletableFuture<?> future) {
        jobFutures.put(jobId, future);
    }

    public void unregisterJob(final long jobId) {
        jobFutures.remove(jobId);
    }

    public List<Long> getRunningJobIds() {
        return new ArrayList<>(jobFutures.keySet());
    }
}

