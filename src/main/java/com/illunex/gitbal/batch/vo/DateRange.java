package com.illunex.gitbal.batch.vo;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateRange(LocalDate date) {
        this.startDate = date;
        this.endDate = date;
    }
}
