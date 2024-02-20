package com.illunex.factsheet.admin.domain.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "login_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long userIdx;

    private String id;

    private String type;

    private String status;

    private LocalDateTime createdAt;

}
