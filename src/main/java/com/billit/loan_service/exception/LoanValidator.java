package com.billit.loan_service.exception;

import com.billit.loan_service.dto.LoanRequestDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class LoanValidator {
    // 대출 신청 전체 유효성 검사
    public void validateLoanRequest(LoanRequestDto request) {
        validateUserBorrowId(request.getUserBorrowId());
        validateLoanAmount(request.getLoanAmount());
        validateLoanTerm(request.getTerm());
    }

    // 대출 금액 유효성 검사
    public void validateLoanAmount(BigDecimal amount) {
        if (amount == null) {
            throw new CustomException(ErrorCode.INVALID_LOAN_AMOUNT, "대출 금액은 필수 입력값입니다.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomException(ErrorCode.INVALID_LOAN_AMOUNT, "대출 금액은 0보다 커야 합니다.");
        }
        if (amount.compareTo(new BigDecimal("100000000")) > 0) {
            throw new CustomException(ErrorCode.INVALID_LOAN_AMOUNT, "최대 대출 가능 금액을 초과하였습니다.");
        }
    }

    // 대출 기간 유효성 검사
    public void validateLoanTerm(Integer term) {
        if (term == null) {
            throw new CustomException(ErrorCode.INVALID_LOAN_TERM, "대출 기간은 필수 입력값입니다.");
        }
        if (term <= 0) {
            throw new CustomException(ErrorCode.INVALID_LOAN_TERM, "대출 기간은 0보다 커야 합니다.");
        }
        if (term > 60) {  // 최대 60개월(5년) 제한
            throw new CustomException(ErrorCode.INVALID_LOAN_TERM, "최대 대출 기간은 60개월입니다.");
        }
    }

    // 대출자 ID 유효성 검사
    public void validateUserBorrowId(Integer userBorrowId) {
        if (userBorrowId == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "대출자 ID는 필수 입력값입니다.");
        }
        if (userBorrowId <= 0) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "유효하지 않은 대출자 ID입니다.");
        }
    }

    // 대출 실행일자 유효성 검사
    public void validateIssueDate(LocalDateTime issueDate) {
        if (issueDate == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "대출 실행일자는 필수 입력값입니다.");
        }
        if (issueDate.isAfter(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "대출 실행일자는 미래일 수 없습니다.");
        }
    }

    // 이자율 유효성 검사
    public void validateInterestRate(BigDecimal interestRate) {
        if (interestRate == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "이자율은 필수 입력값입니다.");
        }
        if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "이자율은 0 이상이어야 합니다.");
        }
        if (interestRate.compareTo(new BigDecimal("20.0")) > 0) {  // 최대 이자율 20% 제한
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE, "최대 이자율을 초과하였습니다.");
        }
    }
}