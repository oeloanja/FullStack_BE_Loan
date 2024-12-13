package com.billit.loan_service.dto;

import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class LoanResponseDto {
    private final Integer loanId;
    private final UUID userBorrowId;
    private final Integer accountBorrowId;
    private final Integer groupId;
    private final BigDecimal loanAmount;
    private final BigDecimal loanLimit;
    private final Integer term;
    private final BigDecimal intRate;
    private final LocalDate issueDate;
    private final LocalDateTime createdAt;
    private final LoanStatusType statusType;

    public LoanResponseDto(Loan loan) {
        this.loanId = loan.getLoanId();
        this.userBorrowId = loan.getUserBorrowId();
        this.accountBorrowId = loan.getAccountBorrowId();
        this.groupId = loan.getGroupId();
        this.loanAmount = loan.getLoanAmount();
        this.loanLimit = loan.getLoanLimit();
        this.term = loan.getTerm();
        this.intRate = loan.getIntRate();
        this.issueDate = loan.getIssueDate();
        this.createdAt = loan.getCreatedAt();
        this.statusType = loan.getLoanStatus().getStatus();
    }

    public static LoanResponseDto from(Loan loan) {
        return new LoanResponseDto(loan);
    }
}
