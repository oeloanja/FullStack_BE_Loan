package com.billit.loan_service.service;

import com.billit.loan_service.dto.LoanStatusRequestDto;
import com.billit.loan_service.dto.LoanStatusResponseDto;
import com.billit.loan_service.dto.LoanSuccessStatusRequestDto;
import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.exception.CustomException;
import com.billit.loan_service.exception.ErrorCode;
import com.billit.loan_service.exception.LoanStatusValidator;
import com.billit.loan_service.repository.LoanRepository;
import com.billit.loan_service.repository.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanStatusService {
    private final LoanStatusRepository loanStatusRepository;
    private final LoanStatusValidator statusValidator;

    // Read
    // 특정 대출의 상태 조회
    public LoanStatusResponseDto getLoanStatus(Integer loanId) {
        LoanStatus status = loanStatusRepository.findByLoanId(loanId);
        return LoanStatusResponseDto.from(status);
    }

    public boolean isLoanGroupStatusExecuting(Integer groupId){
        return loanStatusRepository.existsByGroupIdAndStatus(groupId, LoanStatusType.EXECUTING);
    }

    // Update
    // 상태 업데이트
    @Transactional
    public LoanStatusResponseDto updateLoanStatus(LoanStatusRequestDto request) {
        LoanStatus target = loanStatusRepository.findByLoanId(request.getLoanId());

        if (target == null) {
            throw new CustomException(ErrorCode.LOAN_NOT_FOUND);
        }

        LoanStatusType newStatus = request.getStatusAsEnum();
        statusValidator.validateStatusNotNull(newStatus);

        try {
            statusValidator.validateStatusTransition(target.getStatus(), newStatus);
            target.updateStatus(newStatus);

            if(newStatus == LoanStatusType.EXECUTING) {
                target.getLoan().updateIssueDate(LocalDate.now());
            }

            return LoanStatusResponseDto.from(target);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public LoanStatusResponseDto updateLoanStatusSuccess(LoanSuccessStatusRequestDto request) {
        LoanStatus target = loanStatusRepository.findByLoanId(request.getLoanId());

        if (target == null) {
            throw new CustomException(ErrorCode.LOAN_NOT_FOUND);
        }

        LoanStatusType newStatus = request.getStatusAsEnum();
        statusValidator.validateStatusNotNull(newStatus);

        try {
            statusValidator.validateStatusTransition(target.getStatus(), newStatus);
            target.updateStatus(newStatus);

            if(newStatus == LoanStatusType.EXECUTING) {
                target.getLoan().updateIssueDate(request.getIssueDate());
            }

            return LoanStatusResponseDto.from(target);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
