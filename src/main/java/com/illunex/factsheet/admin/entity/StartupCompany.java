package com.illunex.factsheet.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "startup_company")
public class StartupCompany {

    @Id
    private Long idx;

    private String name;

    private String regNum;

    private String type;

    private String introduction;

    private String description;

    private String bizType;

    private String bizStatus;

    private String industryField;

    private String ceoName;

    private String ceoContact;

    private String ceoEmail;

    private String zipCode;

    private String address;

    private String addressDetail;

    private String country;

    private String homepage;

    private String sales;

    private String salesYear;

    private String license;

    private Integer employeeNum;

    private LocalDateTime estDate;

    private LocalDateTime regDate;

    private LocalDateTime updateDate;

    private Boolean listed;

    private Boolean deleted;

    private Boolean viewMode;

    private String logoSqu;

    private String logoRec;

    private Boolean comCheck;

    private Boolean bizCheck;

    private Boolean irCheck;

    private String certType;

    private String payCycle;

    private Integer payDay;

    private String customerKey;
}
