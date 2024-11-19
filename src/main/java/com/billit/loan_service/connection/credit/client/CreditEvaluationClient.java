package com.billit.loan_service.connection.credit.client;

import com.billit.loan_service.connection.credit.dto.CreditEvaluationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "credit-evaluation-service", url = "http://CREDIT-EVALUATION-SERVICE")
public interface CreditEvaluationClient {
    @GetMapping("/api/v1/credit-evaluation")
    CreditEvaluationResponse getCreditEvaluation(@RequestParam("userBorrowId") Integer userId);
}