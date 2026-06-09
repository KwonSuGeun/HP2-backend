package com.hospital.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS("200", "정상 처리되었습니다."),
    BAD_REQUEST("400", "잘못된 요청입니다."),
    STAFF_NOT_FOUND("404", "직원 정보를 찾을 수 없습니다."),
    INTERNAL_ERROR("500", "서버 오류가 발생했습니다.");

    private final String code;
    private final String message;
}
