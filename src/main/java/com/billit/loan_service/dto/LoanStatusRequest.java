package com.billit.loan_service.dto;

import com.billit.loan_service.enums.LoanStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanStatusRequest {
    private LoanStatusType statusType;
}
