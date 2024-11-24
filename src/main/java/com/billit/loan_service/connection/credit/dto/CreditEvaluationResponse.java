package com.billit.loan_service.connection.credit.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CreditEvaluationResponse {
    private BigDecimal intRate;
}
