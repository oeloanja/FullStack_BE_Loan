package com.billit.loan_service.controller;


import com.billit.loan_service.dto.LoanRequest;
import com.billit.loan_service.dto.LoanResponse;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    // 대출 신청
    @PostMapping("/register")
    public LoanResponse saveLoan(@RequestBody LoanRequest loanRequest) {
        return loanService.createLoan(loanRequest);
    }

    // 사용자 대출 신청 이력 조회
    @GetMapping("/{userBorrowId}")
    public List<LoanResponse> getLoanByUserBorrowId(@PathVariable Integer userBorrowId) {
        return loanService.getUserLoanHistory(userBorrowId);
    }

    // 특정 대출 상세 정보 조회
    @GetMapping("/{loanId}")
    public LoanResponse getLoanById(@PathVariable Integer loanId) {
        return loanService.getLoanById(loanId);
    }

    // 대출 유형 별 조회
    @GetMapping("/{userBorrowId}/{loanStatus}")
    public List<LoanResponse> getUserLoansByStatus(@PathVariable Integer userBorrowId, @PathVariable LoanStatusType loanStatus) {
        return loanService.getUserLoansByStatus(userBorrowId, loanStatus);
    }

    // 특정 계좌 대출중 여부 확인
    @GetMapping("/{userBorrowAccountId}")
    public boolean isExistLoanByUserAccountId(@PathVariable Integer userBorrowAccountId) {
        return loanService.isExistLoanByUserAccountId(userBorrowAccountId);
    }
}
