package com.billit.loan_service.dto;

import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanStatusRequestDto {
    private int loanId;
    private int status;

    public LoanStatusType getStatusAsEnum() {
        return LoanStatusType.values()[this.status];
    }
}
