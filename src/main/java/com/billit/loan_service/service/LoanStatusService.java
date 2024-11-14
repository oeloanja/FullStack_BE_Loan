package com.billit.loan_service.service;

import com.billit.loan_service.dto.LoanStatusRequestDto;
import com.billit.loan_service.dto.LoanStatusResponseDto;
import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.repository.LoanRepository;
import com.billit.loan_service.repository.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanStatusService {
    private final LoanStatusRepository loanStatusRepository;
    private final LoanRepository loanRepository;

    // Read
    // 특정 대출의 상태 조회
    public LoanStatusResponseDto getLoanStatus(Integer loanId) {
        LoanStatus status = loanStatusRepository.findByLoanId(loanId);
        return LoanStatusResponseDto.from(status);
    }

    // Update
    // 상태 업데이트
    @Transactional
    public LoanStatusResponseDto updateLoanStatus(LoanStatusRequestDto request) {
        LoanStatus target = loanStatusRepository.findByLoanId(request.getLoanId());
        LoanStatusType newStatus = request.getStatusAsEnum();

        validateStatusTransition(target.getStatus(), newStatus);
        target.updateStatus(newStatus);

        return LoanStatusResponseDto.from(target);
    }

    private void validateStatusTransition(LoanStatusType currentStatus, LoanStatusType newStatus) {
        if (!isValidStatusTransition(currentStatus, newStatus)) {
            throw new IllegalStateException(
                    String.format("잘못된 상태 변경입니다: %s -> %s", currentStatus, newStatus));
        }
    }

    private boolean isValidStatusTransition(LoanStatusType currentStatus, LoanStatusType newStatus) {
        return switch (currentStatus) {
            case WAITING -> newStatus == LoanStatusType.EXECUTING ||
                    newStatus == LoanStatusType.REJECTED ||
                    newStatus == LoanStatusType.CANCELED;
            case EXECUTING -> newStatus == LoanStatusType.COMPLETED ||
                    newStatus == LoanStatusType.OVERDUE;
            case OVERDUE -> newStatus == LoanStatusType.COMPLETED;
            default -> false;
        };
    }
}
