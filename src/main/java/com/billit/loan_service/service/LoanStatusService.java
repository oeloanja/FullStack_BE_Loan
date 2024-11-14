package com.billit.loan_service.service;

import com.billit.loan_service.dto.LoanStatusRequest;
import com.billit.loan_service.dto.LoanStatusResponse;
import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.repository.LoanRepository;
import com.billit.loan_service.repository.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanStatusService {
    private final LoanStatusRepository loanStatusRepository;
    private final LoanRepository loanRepository;

    // Create
    // 상태 생성
    public LoanStatusResponse createLoanStatus(Integer loanId,LoanStatusRequest loanStatusRequest) {
        Loan loan = loanRepository.findById(loanId.longValue()).orElseThrow();
        LoanStatus loanStatus = new LoanStatus(loan, loanStatusRequest.getStatusType());
        return LoanStatusResponse.convertToLoanStatusResponse(loanStatus);
    }

    // Read
    // 특정 대출의 상태 조회
    public LoanStatusResponse getLoanStatus(Integer loanId) {
        LoanStatus status = loanStatusRepository.findByLoanId(loanId);
        return LoanStatusResponse.convertToLoanStatusResponse(status);
    }

    // Update
    // 상태 업데이트
    @Transactional
    public LoanStatusResponse updateLoanStatus(Integer loanId, LoanStatusRequest loanStatusRequest) {
        LoanStatus status = loanStatusRepository.findByLoanId(loanId);
        status.updateStatus(loanStatusRequest.getStatusType());
        return LoanStatusResponse.convertToLoanStatusResponse(status);
    }
}
