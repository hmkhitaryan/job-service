package com.intervew.jobservice.Introspection;

import com.intervew.jobservice.job.JobCancellationService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/introspect")
public class IntrospectionController {

    private final JobCancellationService cancellationService;

    private final ThreadPoolTaskExecutor taskExecutor;

    public IntrospectionController(JobCancellationService cancellationService, ThreadPoolTaskExecutor taskExecutor) {
        this.cancellationService = cancellationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/running-jobs")
    public List<Long> getRunningJobs() {
        // Retrieve a list of running job IDs
        return cancellationService.getRunningJobIds();
    }

    @GetMapping("/queue-size")
    public int getQueueSize() {
        return taskExecutor.getThreadPoolExecutor().getQueue().size();
    }
}

