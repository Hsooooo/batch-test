package com.illunex.factsheet.admin.domain.startup.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;

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
}
