package com.billit.loan_service.service;
import com.billit.loan_service.repository.LoanRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
}
