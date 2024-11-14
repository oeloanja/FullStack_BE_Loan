package com.billit.loan_service.repository;

import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserBorrowId(Integer userBorrowId);
    @Query("SELECT l FROM Loan l WHERE l.userBorrowId = :userBorrowId AND l.loanStatus.status = :status")
    List<Loan> findByUserBorrowIdAndLoanStatus_Status(@Param("userBorrowId") Integer userBorrowId, @Param("status") LoanStatusType status);
    boolean existsByAccountBorrowIdAndLoanStatus_StatusIn(Integer accountBorrowId, List<LoanStatusType> statuses);
}
