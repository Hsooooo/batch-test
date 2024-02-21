package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

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

    private Boolean paid;

    private Integer gender;

    private String documents;

    private Boolean alarm;

    private Boolean autoConfirm;

    private String type;

    private String refresh;

    private LocalDateTime deletedDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "startupCompanyIdx", referencedColumnName = "idx")
    private StartupCompany company;

}
