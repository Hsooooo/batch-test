package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "hr_annual_vacation_history")
public class HrAnnualVacationHistory {
    @Id
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver", referencedColumnName = "idx")
    private StartupCompanyHrMember hrMember;

    private String event;

    private String memo;

    private Integer amount;

    private Integer currentWorkTime;

    private LocalDate applyDate;

    @CreatedDate
    private LocalDate createdDate;
}
