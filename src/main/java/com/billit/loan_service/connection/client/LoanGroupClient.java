package com.billit.loan_service.connection.client;

import com.billit.loan_service.connection.dto.LoanGroupRequestClientDto;
import com.billit.loan_service.connection.dto.LoanGroupResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "loan-group-service", url = "http://localhost:8084")
public interface LoanGroupClient {
    @PostMapping("/api/v1/loans/group/register")
    LoanGroupResponseClientDto registerLoan(@RequestBody LoanGroupRequestClientDto request);
}
