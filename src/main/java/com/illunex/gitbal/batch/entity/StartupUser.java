package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "startup_user")
public class StartupUser {

    @Id
    private Long idx;

    @Column(unique = true)
    private String id;

    private String pw;

    private String img;

    private String status;

    private String token;

    private String role;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyIdx", referencedColumnName = "idx")
    private StartupCompany company;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private StartupUserProfile startupUserProfile;
}
