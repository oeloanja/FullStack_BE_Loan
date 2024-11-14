package com.billit.loan_service.service;
import com.billit.loan_service.connection.client.BorrowAccountClient;
import com.billit.loan_service.connection.client.CreditEvaluationClient;
import com.billit.loan_service.connection.client.LoanGroupClient;
import com.billit.loan_service.connection.dto.BorrowAccountResponse;
import com.billit.loan_service.connection.dto.CreditEvaluationResponse;
import com.billit.loan_service.connection.dto.LoanGroupResponse;
import com.billit.loan_service.dto.*;
import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private CreditEvaluationClient creditEvaluationClient;
    private LoanGroupClient loanGroupClient;
    private BorrowAccountClient borrowAccountClient;

    // Create
    @Transactional
    // 대출 생성
    public LoanResponse createLoan(LoanRequest loanRequest) {
        CreditEvaluationResponse creditEvaluationResponse = creditEvaluationClient.getCreditEvaluation(loanRequest.getUserBorrowId());
        LoanGroupResponse loanGroupResponse = loanGroupClient.getLoanGroup(loanRequest.getUserBorrowId());
        BorrowAccountResponse borrowAccountResponse = borrowAccountClient.getBorrowAccountId(loanRequest.getUserBorrowId());
        Loan loan = new Loan(loanRequest.getUserBorrowId(),
                loanGroupResponse.getGroupId(),
                borrowAccountResponse.getAccountBorrowId(),
                loanRequest.getLoanAmount(),
                loanRequest.getTerm(),
                creditEvaluationResponse.getIntRate(),
                LocalDateTime.now());
        loanRepository.save(loan);
        return LoanResponse.convertToLoanResponse(loan);
    }

    // Read
    // 대출 이력(상태 무관) 조회
    public List<LoanResponse> getUserLoanHistory(Integer userBorrowId) {
        List<Loan> loans = loanRepository.findByUserBorrowId(userBorrowId);
        return loans.stream().map(LoanResponse::convertToLoanResponse).toList();
    }

    // 대출 유형 별 조회
    public List<LoanResponse> getUserLoansByStatus(Integer userBorrowId, LoanStatusType status) {
        List<Loan> loans = loanRepository.findByUserBorrowIdAndLoanStatus_Status(userBorrowId, status);
        return loans.stream().map(LoanResponse::convertToLoanResponse).toList();
    }

    // 특정 대출 상세정보 조회
    public LoanResponse getLoanById(Integer loanId) {
        return loanRepository.findById(Long.valueOf(loanId))
                .map(LoanResponse::convertToLoanResponse)
                .orElse(null);
    }

    // 계좌고유번호로 대출 있는지 여부 확인 메소드 (있으면 true, 없으면 false)
    public boolean isExistLoanByUserAccountId(Integer userAccountId){
        return loanRepository.existsByAccountBorrowIdAndLoanStatus_Status(userAccountId, LoanStatusType.EXECUTING);
    }
}
