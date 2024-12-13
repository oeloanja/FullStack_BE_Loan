package com.billit.loan_service.controller;

import com.billit.loan_service.dto.LoanStatusRequestDto;
import com.billit.loan_service.dto.LoanStatusResponseDto;
import com.billit.loan_service.dto.LoanSuccessStatusRequestDto;
import com.billit.loan_service.service.LoanStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loan-service")
public class LoanStatusController {
    private final LoanStatusService loanStatusService;

    // 대출 상태 조회
    @GetMapping("/{loanId}/status")
    public LoanStatusResponseDto getLoanStatus(@PathVariable Integer loanId) {
        return loanStatusService.getLoanStatus(loanId);
    }

    // groupId로 대출 상태 조회
    @GetMapping("/{groupId}/status")
    public boolean isLoanGroupStatusExecuting(@PathVariable Integer groupId){
        return loanStatusService.isLoanGroupStatusExecuting(groupId);
    }

    // 대출 상태 업데이트
    @PutMapping("/status")
    public LoanStatusResponseDto updateLoanStatus(@RequestBody LoanStatusRequestDto request) {
        return loanStatusService.updateLoanStatus(request);
    }

    @PutMapping("/status/success")
    public LoanStatusResponseDto updateLoanStatusSuccess(@RequestBody LoanSuccessStatusRequestDto request) {
        return loanStatusService.updateLoanStatusSuccess(request);
    }
}
