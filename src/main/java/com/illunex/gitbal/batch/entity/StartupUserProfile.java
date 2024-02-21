package com.illunex.gitbal.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "startup_user")
public class StartupUserProfile {
    @Id
    private Long idx;

    private LocalDate joinDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userIdx", referencedColumnName = "idx")
    private StartupUser user;
}
