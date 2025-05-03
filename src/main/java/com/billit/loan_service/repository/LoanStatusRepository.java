package com.billit.loan_service.repository;

import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanStatusRepository extends JpaRepository<LoanStatus, Long> {
    LoanStatus findByLoanId(long loanId);

    @Query("SELECT CASE WHEN COUNT(ls) > 0 THEN true ELSE false END " +
            "FROM LoanStatus ls " +
            "JOIN ls.loan l " +
            "WHERE l.groupId = :groupId AND ls.status = :status")
    boolean existsByGroupIdAndStatus(@Param("groupId") Integer groupId, @Param("status") LoanStatusType status);
}
