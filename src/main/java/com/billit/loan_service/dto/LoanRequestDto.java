package com.billit.loan_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDto {
    private UUID userBorrowId;
    private Integer accountBorrowId;
    private BigDecimal loanAmount;
    private Integer term;
//    private BigDecimal intRate;
}
