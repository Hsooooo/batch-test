package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "startup_company_hr_member")
public class StartupCompanyHrMember {
    @Id
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyIdx", referencedColumnName = "idx")
    private StartupCompany company;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userIdx", referencedColumnName = "idx")
    private StartupUser user;
}
