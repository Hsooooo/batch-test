package com.illunex.gitbal.batch.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 엔티티 관련 코드는 엔티티에서 관리하도록 변경
 */
@Deprecated(since = "2024-01-17")
public class CommonCode {

    /**
     * 기업 코드
     */
    public static class Company {

        @Getter
        @AllArgsConstructor
        public enum Status {
            STOP("CS01", "휴업"),
            CLOSED("CS02", "폐업"),
            CONTINUE("CS03", "유지"),
            CANCELLATION("CS04", "말소"),
            MERGE("CS05", "합병");

            private String code;
            private String desc;

            public static Status codeToValue(String stringCode) {
                for (Status value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum StockKind {
            COMMON_STOCK("C01", "보통주"),
            REDEEMABLE_CONVERTIBLE_PREFERRED_STOCK("C02", "상환전환우선주"),
            CONVERTIBLE_PREFERRED_STOCK("C03", "전환우선주"),
            REDEEMABLE_PREFERRED_STOCK("C04", "상환우선주"),
            STOCK_WARRANT_BOND("C05", "주식인수권부사채(CB)"),
            PREFERRED_STOCK("C06", "우선주");

            private String code;
            private String desc;

            public static StockKind codeToValue(String stringCode) {
                for (StockKind value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum ShareholderGubun {
            LARGEST_SHAREHOLDER("10", "최대주주"),
            LARGEST_SHAREHOLDER_RELATED("20", "최대주주의특수관계인"),
            MORE_THAN_FIVE_PERCENT("30", "5%이상주주"),
            ETC("40", "기타주주"),
            EMPLOYEE_STOCK_OWNERSHIP_ASSOCIATION("50", "우리사주조합");

            private String code;
            private String desc;

            public static ShareholderGubun codeToValue(String stringCode) {
                for (ShareholderGubun value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum  IndustryCategoryCd {
            ICT_SERVICE("IDSC_01","ICT서비스"),
            ICT_MANUFACTURING("IDSC_02","ICT제조"),
            ELECTRICITY_MACHINE_EQUIPMENT("IDSC_03","전기/기계/장비"),
            CHEMISTRY_MATERIAL("IDSC_04","화학/소재"),
            BIO_HEALTHCARE("IDSC_05","바이오/의료"),
            VIDEO_PERFORMANCE_RECORD("IDSC_06","영상/공연/음반"),
            GAME("IDSC_07","게임"),
            CIRCULATION_SERVICE("IDSC_08","유통/서비스"),
            ETC("IDSC_99","기타");

            private String code;
            private String desc;

            public static IndustryCategoryCd codeToValue(String stringCode) {
                for (IndustryCategoryCd value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum  IndustrialFieldCd {
            TECH_SOLUTION("IDSC_01_01","Tech 솔루션"),
            APPLICATION_PROGRAM("IDSC_01_02","어플리케이션/프로그램"),
            BUSINESS_SOLUTIONS("IDSC_01_03","비즈니스 솔루션"),
            INFORMATION_MANAGEMENT_SOLUTIONS("IDSC_01_04","정보관리 솔루션"),
            FINANCIAL_INSURANCE("IDSC_01_05","금융/보험"),
            TRAVEL_ACCOMMODATION("IDSC_01_06","여행/숙박"),
            TELECOMMUNICATIONS_NETWORKS("IDSC_01_07","통신/네트워크"),
            SALES_MARKETING("IDSC_01_08","영업/마케팅"),
            EXHIBITION_PERFORMANCE_MUSIC("IDSC_01_09","전시/공연/음악"),
            VEHICLE_TRAFFIC("IDSC_01_10","차량/교통"),
            BEAUTY_FASHION("IDSC_01_11","뷰티/패션"),
            SERVICE_PLATFORM("IDSC_01_12","서비스 플랫폼"),
            SOCIAL_NETWORKS("IDSC_01_13","소셜네트워크");

            private String code;
            private String desc;

            public static IndustrialFieldCd codeToValue(String stringCode) {
                for (IndustrialFieldCd value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum File {
            BUSINESS("FILE01","사업자등록증"),
            REGISTERED("FILE02","등기본등본"),
            PLAN("FILE03","사업계획서");

            private String code;
            private String desc;

            public static File codeToValue(String stringCode) {
                for (File value : values()) {
                    if (value.code.equals(stringCode)) {
                        return value;
                    }
                }
                return null;
            }
        }
    }
}
