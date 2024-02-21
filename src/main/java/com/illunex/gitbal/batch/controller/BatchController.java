package com.illunex.gitbal.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
    private final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job dailyVacationJob;

    @GetMapping("/batch")
    public ResponseEntity launch() throws Exception {
        logger.info("----------------");
        jobLauncher.run(dailyVacationJob, new JobParameters());

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/test")
    public String test() {
        logger.info("----------------");

        return "test";
    }
}
