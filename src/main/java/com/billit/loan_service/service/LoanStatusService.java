package com.billit.loan_service.service;

import com.billit.loan_service.repository.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanStatusService {
    private final LoanStatusRepository loanStatusRepository;
}
