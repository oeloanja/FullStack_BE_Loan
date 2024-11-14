package com.billit.loan_service.controller;

import com.billit.loan_service.dto.LoanStatusRequest;
import com.billit.loan_service.dto.LoanStatusResponse;
import com.billit.loan_service.service.LoanStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans/status")
public class LoanStatusController {
    private final LoanStatusService loanStatusService;

    //대출 상태 생성
    @PostMapping("/{loanId}/create")
    public LoanStatusResponse create(@PathVariable Integer loanId, @RequestBody LoanStatusRequest loanStatusRequest) {
        return loanStatusService.createLoanStatus(loanId, loanStatusRequest);
    }

    @GetMapping("/{loanId}")
    public LoanStatusResponse get(@PathVariable Integer loanId) {
        return loanStatusService.getLoanStatus(loanId);
    }
}
