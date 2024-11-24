package com.billit.loan_service.controller;

import com.billit.loan_service.dto.LoanStatusRequestDto;
import com.billit.loan_service.dto.LoanStatusResponseDto;
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

    // 대출 상태 업데이트
    @PutMapping("/status")
    public LoanStatusResponseDto updateLoanStatus(@RequestBody LoanStatusRequestDto request) {
        return loanStatusService.updateLoanStatus(request);
    }
}
