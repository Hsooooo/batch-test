package com.illunex.factsheet.admin.error;

public class ErrorMsg {

    public static final String NOT_EXIST = "존재 하지 않습니다.";
    public static final String INVALID = "유효하지 않습니다.";
    public static final String LOGIN_AUTHENTICATION = "로그인이 필요합니다.";
    public static final String UNAUTHORIZED = "접근 권한이 없습니다.";
    public static final String DUPLICATED = "중복된 값이 존재합니다.";
    public static final String REQUIRED = "필수 값이 없습니다.";
    public static final String ADMIN_STOP = "정지된 계정입니다.";

    public static String getNotExistMsg(String msg) {
        return msg + " " + NOT_EXIST;
    }

    public static String getInvalidMsg(String msg) {
        return msg + " " + INVALID;
    }

    public static String getDuplicatedMsg(String msg) {
        return msg + " " + DUPLICATED;
    }

    public static String getRequiredMsg(String msg) {
        return msg + " " + REQUIRED;
    }
}
