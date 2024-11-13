package com.billit.loan_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_id")
    private Long loanId;

    @Column(name = "user_borrow_id", nullable = false)
    private Long userBorrowId;

    @Column(name = "loan_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal loanAmount; // 대출 금액

    @Column(name = "term", nullable = false)
    private Integer term; // 대출 기간

    @Column(name = "int_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal intRate; // 이자율

    @Column(name = "issue_date")
    private LocalDate issueDate; // 대출금 실제 입금일

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
}
