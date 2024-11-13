package com.billit.loan_service.repository;

import com.billit.loan_service.entity.LoanStatus;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanStatusRepository {
    List<LoanStatus> findByLoanId(long loanId);
}
