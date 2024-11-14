package com.billit.loan_service.dto;

import com.billit.loan_service.enums.LoanStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LoanRequest {
    private Integer userBorrowId;
    private BigDecimal loanAmount;
    private Integer term;
}
