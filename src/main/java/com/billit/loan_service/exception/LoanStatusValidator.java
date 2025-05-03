package com.billit.loan_service.exception;

import com.billit.loan_service.enums.LoanStatusType;
import org.springframework.stereotype.Component;

@Component
public class LoanStatusValidator {

    public void validateStatusTransition(LoanStatusType currentStatus, LoanStatusType newStatus) {
        if (!isValidStatusTransition(currentStatus, newStatus)) {
            throw new CustomException(
                    ErrorCode.INVALID_STATUS_TRANSITION,
                    String.format("잘못된 상태 변경입니다: %s -> %s", currentStatus, newStatus)
            );
        }
    }

    private boolean isValidStatusTransition(LoanStatusType currentStatus, LoanStatusType newStatus) {
        return switch (currentStatus) {
            case WAITING -> isValidWaitingTransition(newStatus);
            case EXECUTING -> isValidExecutingTransition(newStatus);
            case OVERDUE -> isValidOverdueTransition(newStatus);
            default -> false;
        };
    }

    private boolean isValidWaitingTransition(LoanStatusType newStatus) {
        return newStatus == LoanStatusType.EXECUTING ||
                newStatus == LoanStatusType.REJECTED ||
                newStatus == LoanStatusType.CANCELED;
    }

    private boolean isValidExecutingTransition(LoanStatusType newStatus) {
        return newStatus == LoanStatusType.COMPLETED ||
                newStatus == LoanStatusType.OVERDUE;
    }

    private boolean isValidOverdueTransition(LoanStatusType newStatus) {
        return newStatus == LoanStatusType.COMPLETED;
    }

    // 추가적인 상태 검증 메소드들
    public void validateInitialStatus(LoanStatusType status) {
        if (status != LoanStatusType.WAITING) {
            throw new CustomException(
                    ErrorCode.INVALID_STATUS_TRANSITION,
                    "신규 대출의 초기 상태는 WAITING이어야 합니다."
            );
        }
    }

    public void validateStatusNotNull(LoanStatusType status) {
        if (status == null) {
            throw new CustomException(
                    ErrorCode.INVALID_INPUT_VALUE,
                    "대출 상태는 null일 수 없습니다."
            );
        }
    }
}