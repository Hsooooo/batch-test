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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    public HrAnnualVacationHistory() {}

    public HrAnnualVacationHistory(StartupCompanyHrMember receiver, String event, String memo, Integer amount, Integer currentWorkTime, LocalDate applyDate) {
        this.receiver = receiver;
        this.event = event;
        this.memo = memo;
        this.amount = amount;
        this.currentWorkTime = currentWorkTime;
        this.applyDate = applyDate;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver", referencedColumnName = "idx")
    private StartupCompanyHrMember receiver;

    private String event;

    private String memo;

    private Integer amount;

    private Integer currentWorkTime;

    private LocalDate applyDate;

    @CreatedDate
    private LocalDate createdDate;

}
