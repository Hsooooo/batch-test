package com.illunex.factsheet.admin.entity;

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

    public static List<DateRange> getDateRanges(LocalDate date) {

        // 날짜 형식을 설정합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<DateRange> result = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            LocalDate targetDate = date.minusMonths(i + 1);
            LocalDate startDate;
            LocalDate endDate;
            // 해당 월의 마지막 날짜인 경우
            if (date.lengthOfMonth() == date.getDayOfMonth()) {
                if (targetDate.lengthOfMonth() > date.getDayOfMonth()) {
                    System.out.println((i + 1) + ") " + targetDate.format(formatter) + " ~ " + targetDate.withDayOfMonth(targetDate.lengthOfMonth()).format(formatter));
                    result.add(new DateRange(targetDate, targetDate.withDayOfMonth(targetDate.lengthOfMonth())));
                } else if (targetDate.lengthOfMonth() == date.getDayOfMonth()){
                    result.add(new DateRange(targetDate));
                    System.out.println((i + 1) + ") " + targetDate.format(formatter));
                }
            } else if(targetDate.lengthOfMonth() >= date.getDayOfMonth()) {
                System.out.println((i + 1) + ") " + targetDate.format(formatter));
                result.add(new DateRange(targetDate));
            }
        }

        return result;
    }

    public static class DateRange {
        private LocalDate startDate;
        private LocalDate endDate;

        public DateRange(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public DateRange(LocalDate date) {
            this.startDate = date;
            this.endDate = date;
        }
    }

    public static void main(String[] args) {
        // 테스트를 위한 예시 날짜를 생성합니다.
        LocalDate testDate1 = LocalDate.of(2024, 2, 29);
        LocalDate testDate2 = LocalDate.of(2024, 2, 27);
        LocalDate testDate3 = LocalDate.of(2024, 12, 30);

        // 각각의 예시 날짜에 대해 printDateRanges 메서드를 호출하여 결과를 출력합니다.
        System.out.println("Case 1:");
        System.out.println("\nCase 2:");
        System.out.println("\nCase 3:");
    }
}
