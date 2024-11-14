package com.billit.loan_service.repository;

import com.billit.loan_service.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanStatusRepository extends JpaRepository<LoanStatus, Long> {
    LoanStatus findByLoanId(long loanId);
}
