package com.illunex.factsheet.admin.domain.startup.hr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "hr_vacation_type")
public class HrVacationType {

    @Id
    private Long idx;

    private String title;

    private Integer days;

    private Boolean weekend;

    private Boolean continuous;
}
