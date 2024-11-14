package com.billit.loan_service.dto;

import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import lombok.Getter;

@Getter
public class LoanStatusResponse {
    private final Integer loanId;
    private final LoanStatusType statusType;

    public LoanStatusResponse(LoanStatus loanStatus) {
        this.loanId = loanStatus.getLoanId();
        this.statusType = loanStatus.getStatus();
    }

    public static LoanStatusResponse convertToLoanStatusResponse(LoanStatus loanStatus) {
        return new LoanStatusResponse(loanStatus);
    }
}
