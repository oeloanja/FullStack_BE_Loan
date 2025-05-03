package com.billit.loan_service.connection.loan_group.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
