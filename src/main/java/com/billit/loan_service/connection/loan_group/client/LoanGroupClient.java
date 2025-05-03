package com.billit.loan_service.connection.loan_group.client;

import com.billit.loan_service.connection.loan_group.dto.LoanGroupRequestClientDto;
import com.billit.loan_service.connection.loan_group.dto.LoanGroupResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "LOAN-GROUP-SERVICE", url="${feign.client.config.loan-group-service.url}")
public interface LoanGroupClient {
    @PostMapping("/api/v1/loan-group-service/register")
    LoanGroupResponseClientDto registerLoan(@RequestBody LoanGroupRequestClientDto request);
}
