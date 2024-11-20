package com.billit.loan_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Loan 관련 에러
    LOAN_NOT_FOUND(HttpStatus.NOT_FOUND, "대출을 찾을 수 없습니다."),
    INVALID_LOAN_AMOUNT(HttpStatus.BAD_REQUEST, "유효하지 않은 대출 금액입니다."),
    INVALID_LOAN_TERM(HttpStatus.BAD_REQUEST, "유효하지 않은 대출 기간입니다."),
    INVALID_STATUS_TRANSITION(HttpStatus.BAD_REQUEST, "잘못된 상태 변경입니다."),
    DUPLICATE_LOAN_EXISTS(HttpStatus.CONFLICT, "이미 진행 중인 대출이 있습니다."),
    GROUP_ASSIGNMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "대출 그룹 배정에 실패했습니다."),

    // 일반적인 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}