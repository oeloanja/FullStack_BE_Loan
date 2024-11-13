package com.billit.loan_service.repository;

import com.billit.loan_service.entity.Loan;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByUserBorrowId(Long userBorrowId);

}
