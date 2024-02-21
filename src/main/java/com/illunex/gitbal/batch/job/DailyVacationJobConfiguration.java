package com.illunex.gitbal.batch.job;

import com.illunex.gitbal.batch.entity.StartupUserProfile;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class DailyVacationJobConfiguration extends DefaultBatchConfiguration {

    private final EntityManagerFactory entityManagerFactory;
    private final PlatformTransactionManager transactionManager;

    private final int chunkSize = 100;

    @Bean
    public Job dailyVacationJob(JobRepository jobRepository) {
        return new JobBuilder("dailyVacationJob", jobRepository)
                .start(testStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step testStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("testStep", jobRepository)
                .<StartupUserProfile, StartupUserProfile>chunk(chunkSize, transactionManager)
                .reader(getMonthlyVacationMember())
                .writer(test())
                .build();
    }

    @Bean
    public JpaPagingItemReader<StartupUserProfile> getMonthlyVacationMember() {
        return new JpaPagingItemReaderBuilder<StartupUserProfile>()
                .name("getMonthlyVacationMember")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT sup FROM StartupUserProfile sup WHERE sup.joinDate < now()")
                .build();
    }

    @Bean
    public ItemWriter<StartupUserProfile> test() {
        return list -> {
            for (StartupUserProfile profile : list) {
                System.out.println(profile.getJoinDate());
            }
        };
    }
}
