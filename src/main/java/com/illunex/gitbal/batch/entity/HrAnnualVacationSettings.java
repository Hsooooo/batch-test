package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "hr_annual_vacation_settings")
public class HrAnnualVacationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String unit;

    private Integer defaultRestDays;

    private String vacationPolicy;

    private Boolean escalation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vacationIdx", referencedColumnName = "idx")
    private HrVacationType vacationType;

}
