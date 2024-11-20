package com.billit.loan_service.dto;

import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import lombok.Getter;

import java.util.Optional;

@Getter
public class LoanStatusResponseDto {
    private final Integer loanId;
    private final LoanStatusType statusType;

    public LoanStatusResponseDto(LoanStatus loanStatus){
        this.loanId = loanStatus.getLoanId();
        this.statusType = loanStatus.getStatus();
    }

    public static LoanStatusResponseDto from(LoanStatus loanStatus) {
        return new LoanStatusResponseDto(loanStatus);
    }
}
