package com.billit.loan_service.dto;

import com.billit.loan_service.enums.LoanStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSuccessStatusRequestDto {
    private Integer loanId;
    private int status;
    private LocalDate issueDate;

    public LoanStatusType getStatusAsEnum() {
        return LoanStatusType.values()[this.status];
    }
}
