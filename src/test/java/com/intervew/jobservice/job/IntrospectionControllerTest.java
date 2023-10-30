package com.intervew.jobservice.job;

import com.intervew.jobservice.Introspection.IntrospectionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

public class IntrospectionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private IntrospectionController introspectionController;

    @Mock
    private JobCancellationService jobCancellationService;

    @Mock
    private ThreadPoolTaskExecutor taskExecutor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(introspectionController).build();
    }

    @Test
    public void testGetRunningJobs() throws Exception {
        // Mock running job IDs
        List<Long> runningJobIds = new ArrayList<>();
        runningJobIds.add(1L);
        runningJobIds.add(2L);
        runningJobIds.add(3L);

        when(jobCancellationService.getRunningJobIds()).thenReturn(runningJobIds);

        mockMvc.perform(MockMvcRequestBuilders.get("/introspect/running-jobs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[1, 2, 3]")); // Expected JSON response
    }

    @Test
    public void testGetQueueSize() throws Exception {
        // Mock the ThreadPoolExecutor and its queue
        ThreadPoolExecutor mockThreadPoolExecutor = new ThreadPoolExecutor(
                2, 2, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        when(taskExecutor.getThreadPoolExecutor()).thenReturn(mockThreadPoolExecutor);

        mockMvc.perform(MockMvcRequestBuilders.get("/introspect/queue-size"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0")); // Expected queue size
    }
}
