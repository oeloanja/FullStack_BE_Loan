package com.billit.loan_service.controller;

import com.billit.loan_service.connection.loan_group.dto.LoanGroupResponseClientDto;
import com.billit.loan_service.dto.LoanRequestDto;
import com.billit.loan_service.dto.LoanResponseDto;
import com.billit.loan_service.dto.RepaymentResponseDto;
import com.billit.loan_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loan-service")
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
    public List<LoanResponseDto> getLoanByUserBorrowId(@PathVariable UUID userBorrowId) {
        return loanService.getUserLoanHistory(userBorrowId);
    }

    // 특정 대출 상세 정보 조회
    @GetMapping("/detail/{loanId}")
    public LoanResponseDto getLoanById(@PathVariable Integer loanId) {
        return loanService.getLoanById(loanId);
    }

    // 특정 대출 사용자 정보 조회
    @GetMapping("/user")
    public RepaymentResponseDto getLoanUserById(@RequestParam Integer loanId) {return loanService.getLoanUserById(loanId);}

    //그룹 별 대출 조회
    @GetMapping("/list/{groupId}")
    public List<LoanResponseDto> getLoansByGroupId(@PathVariable Integer groupId) {
        return loanService.getLoansByGroupId(groupId);
    }

    // 이자율 평균 계산
    @GetMapping("/group/{groupId}/average-rate")
    public Double getAverageInterestRateByGroupId(@PathVariable Integer groupId) {
        return loanService.calculateAverageIntRate(groupId);
    }

    // 이율 업데이트
    @PutMapping("/{loanId}/update-rate")
    public void updateLoanInterestRate(
            @PathVariable Integer loanId,
            @RequestParam BigDecimal newRate) {
        loanService.updateLoanInterestRate(loanId, newRate);
    }

    // 대출 상태 별 조회
    @GetMapping("/history/{userBorrowId}/filter")
    public List<LoanResponseDto> getUserLoansByStatus(@PathVariable UUID userBorrowId, @RequestParam int loanStatus) {
        return loanService.getUserLoansByStatus(userBorrowId, loanStatus);
    }
}
