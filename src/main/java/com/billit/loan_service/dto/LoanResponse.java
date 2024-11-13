package com.billit.loan_service.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;

@Getter
public class LoanResponse {
    private final Long loanId;
    private final Long userBorrowId;
    private final Long groupId;
    private final BigDecimal loanAmount;
    private final Integer term;
    private final BigDecimal intRate;
    private final LocalDate issueDate;
    private final LocalDateTime createdAt;
    private final LoanStatusType statusType;

    public LoanResponse(Loan loan) {
        this.loanId = loan.getLoanId();
        this.userBorrowId = loan.getUserBorrowId();
        this.groupId = loan.getGroupId();
        this.loanAmount = loan.getLoanAmount();
        this.term = loan.getTerm();
        this.intRate = loan.getIntRate();
        this.issueDate = loan.getIssueDate();
        this.createdAt = loan.getCreatedAt();
        this.statusType = loan.getLoanStatus().getStatus();
    }
}
