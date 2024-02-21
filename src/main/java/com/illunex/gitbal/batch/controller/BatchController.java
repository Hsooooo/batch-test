package com.illunex.gitbal.batch.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/batch")
public class BatchController {
    private final Logger logger = LoggerFactory.getLogger(BatchController.class);

    private final JobLauncher jobLauncher;
    private final Job dailyVacationJob;

    @GetMapping("/{today}")
    public ResponseEntity launch(@PathVariable String today) throws Exception {
        logger.info("----------------");
        Map<String, JobParameter<?>> map = new HashMap<>();
        JobParameter<?> parameter = new JobParameter<>(today, String.class);
        map.put("today", parameter);
        jobLauncher.run(dailyVacationJob, new JobParameters(map));

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/test")
    public String test() {
        logger.info("----------------");

        return "test";
    }
}
