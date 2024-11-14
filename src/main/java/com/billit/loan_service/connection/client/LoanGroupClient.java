package com.billit.loan_service.connection.client;

import com.billit.loan_service.connection.dto.LoanGroupResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loan-group-service")
public interface LoanGroupClient {
    @GetMapping("/api/v1/loan-group")
    LoanGroupResponse getLoanGroup(@RequestParam("userBorrowId") Integer userBorrowId);
}
