package com.illunex.gitbal.batch.job;

import com.illunex.gitbal.batch.entity.*;
import com.illunex.gitbal.batch.vo.DateRange;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DailyVacationJobConfiguration extends DefaultBatchConfiguration {

    private final EntityManagerFactory entityManagerFactory;
    private final PlatformTransactionManager transactionManager;

    private final int chunkSize = 100;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Bean
    public Job dailyVacationJob(JobRepository jobRepository) {
        return new JobBuilder("dailyVacationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(testStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    @JobScope
    public Step testStep(JobRepository jobRepository,
                         PlatformTransactionManager transactionManager) {
        return new StepBuilder("testStep", jobRepository)
                .<StartupUserProfile, HrAnnualVacationHistory>chunk(chunkSize, transactionManager)
                .reader(getMonthlyVacationMember(null))
                .processor(processor())
                .writer(test())
                .build();
    }

    private List<DateRange> getDateRanges(LocalDate date) {
        // 날짜 형식을 설정합니다.

        List<DateRange> result = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            LocalDate targetDate = date.minusMonths(i + 1);
            // 해당 월의 마지막 날짜인 경우
            if (date.lengthOfMonth() == date.getDayOfMonth()) {
                if (targetDate.lengthOfMonth() > date.getDayOfMonth()) {
                    result.add(new DateRange(targetDate, targetDate.withDayOfMonth(targetDate.lengthOfMonth())));
                } else if (targetDate.lengthOfMonth() == date.getDayOfMonth()){
                    result.add(new DateRange(targetDate));
                }
            } else if(targetDate.lengthOfMonth() >= date.getDayOfMonth()) {
                result.add(new DateRange(targetDate));
            }
        }

        return result;
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<StartupUserProfile> getMonthlyVacationMember(@Value("#{JobParameters[today]}") String today) {
        LocalDate todayDate = LocalDate.now();
        if (StringUtils.isNotBlank(today)) {
            todayDate = LocalDate.parse(today, formatter);
        }
        List<DateRange> dateRanges = getDateRanges(todayDate);
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT sup FROM StartupUserProfile sup WHERE ");

        int index = 0;
        Map<String, Object> parameters = new HashMap<>();
        for (DateRange dateRange : dateRanges) {
            String paramNameStart = "startDate" + index;
            String paramNameEnd = "endDate" + index;

            queryStringBuilder.append(" (sup.joinDate >= :").append(paramNameStart).append(" AND sup.joinDate <= :").append(paramNameEnd).append(") OR");

            parameters.put(paramNameStart, dateRange.getStartDate());
            parameters.put(paramNameEnd, dateRange.getEndDate());

            index++;
        }

        // 맨 마지막 OR 제거
        queryStringBuilder.delete(queryStringBuilder.length() - 3, queryStringBuilder.length());

        return new JpaPagingItemReaderBuilder<StartupUserProfile>()
                .name("getMonthlyVacationMember")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString(queryStringBuilder.toString())
                .parameterValues(parameters)
                .build();
    }

    @Bean
    public ItemProcessor<StartupUserProfile, HrAnnualVacationHistory> processor() {
        return userProfile -> {
            int currentWorkTime = 0; // 구하는 코드 짜야함
            StartupUser su = userProfile.getUser();
            StartupCompanyHrMember schm = su.getHrMember();
            return new HrAnnualVacationHistory(userProfile.getUser().getHrMember(), "autoVplus", "", 480, 480, LocalDate.now());
        };
    }

    @Bean
    public JpaItemWriter<HrAnnualVacationHistory> test() {
        return new JpaItemWriterBuilder<HrAnnualVacationHistory>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}
