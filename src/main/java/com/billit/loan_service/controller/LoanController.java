package com.billit.loan_service.controller;


import com.billit.loan_service.connection.dto.LoanGroupResponseClientDto;
import com.billit.loan_service.dto.LoanRequestDto;
import com.billit.loan_service.dto.LoanResponseDto;
import com.billit.loan_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    // 대출 신청 : 성공
    @PostMapping("/register/success")
    public LoanResponseDto saveLoanSuccess(@RequestBody LoanRequestDto request) {
        return loanService.createLoanSuccess(request);
    }

    // 그룹 배정
    @PutMapping("/{loanId}/assign-group")
    public LoanGroupResponseClientDto assignGroupToLoan(@PathVariable Integer loanId) {
        return loanService.assignGroupToLoan(loanId);
    }

    // 대출 신청 : 거절
    @PostMapping("/register/reject")
    public LoanResponseDto saveLoanReject(@RequestBody LoanRequestDto request) {
        return loanService.createLoanReject(request);
    }

    // 사용자 대출 신청 이력 조회
    @GetMapping("/history/{userBorrowId}")
    public List<LoanResponseDto> getLoanByUserBorrowId(@PathVariable Integer userBorrowId) {
        return loanService.getUserLoanHistory(userBorrowId);
    }

    // 특정 대출 상세 정보 조회
    @GetMapping("/detail/{loanId}")
    public LoanResponseDto getLoanById(@PathVariable Integer loanId) {
        return loanService.getLoanById(loanId);
    }

    //그룹 별 대출 조회
    @GetMapping("/api/v1/loans/list/{groupId}")
    public List<LoanResponseDto> getLoansByGroupId(@PathVariable Integer groupId) {
        return loanService.getLoansByGroupId(groupId);
    }

    // 이자율 평균 계산
    @GetMapping("/group/{groupId}/average-rate")
    public Double getAverageInterestRateByGroupId(@PathVariable Integer groupId) {
        return loanService.calculateAverageIntRate(groupId);
    }

    // 대출 유형 별 조회
    @GetMapping("/history/{userBorrowId}/{loanStatus}")
    public List<LoanResponseDto> getUserLoansByStatus(@PathVariable Integer userBorrowId, @PathVariable int loanStatus) {
        return loanService.getUserLoansByStatus(userBorrowId, loanStatus);
    }

    // 특정 계좌 대출중 여부 확인
    @GetMapping("/check/{userBorrowAccountId}")
    public boolean isExistLoanByUserAccountId(@PathVariable Integer userBorrowAccountId) {
        return loanService.isExistLoanByUserAccountId(userBorrowAccountId);
    }
}
