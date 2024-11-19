package com.billit.loan_service.connection.user.client;

import com.billit.loan_service.connection.user.dto.BorrowAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "borrow-account-service", url = "http://BORROW-ACCOUNT-SERVICE")
public interface BorrowAccountClient {
    @GetMapping("/api/v1/borrow-account-service")
    BorrowAccountResponse getBorrowAccountId(@RequestParam("loanId") Integer loanId);
}
