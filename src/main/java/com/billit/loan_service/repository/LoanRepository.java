package com.billit.loan_service.repository;

import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserBorrowId(Integer userBorrowId);
    List<Loan> findByUserBorrowIdAndLoanStatus_Status(Integer userBorrowId, LoanStatusType loanStatus_status);
    boolean existsByAccountBorrowIdAndLoanStatus_Status(Integer accountBorrowId, LoanStatusType loanStatus_status);
}
