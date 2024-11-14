package com.billit.loan_service.dto;

import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDto {
    private Integer userBorrowId;
    private BigDecimal loanAmount;
    private Integer term;
//    private LoanStatusType status;
}
