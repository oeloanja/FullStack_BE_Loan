package com.billit.loan_service.connection.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class LoanGroupRequestClientDto {
    private Integer loanId;

    public LoanGroupRequestClientDto(Integer loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("loanId must not be null");
        }
        this.loanId = loanId;
    }
}
