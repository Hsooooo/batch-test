package com.illunex.gitbal.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job dailyVacationJob;

    @GetMapping("/batch")
    public String launch() throws Exception {
        jobLauncher.run(dailyVacationJob, null);

        return "ok";
    }
}
