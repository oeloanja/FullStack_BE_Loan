package com.billit.loan_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    @Column(nullable = false)
    private Integer userBorrowId;

    @Column(precision = 15, scale = 2)
    private BigDecimal loanAmount; // 대출 금액

    @Column()
    private Integer term; // 대출 기간

    @Column(precision = 5, scale = 2)
    private BigDecimal intRate; // 이자율

    @Column
    private LocalDate issueDate; // 대출금 실제 입금일

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private Integer groupId;

    @Column
    private Integer accountBorrowId;

    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private LoanStatus loanStatus;

    public void updateIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Loan(Integer userBorrowId, Integer groupId, Integer accountBorrowId, BigDecimal loanAmount, Integer term, BigDecimal intRate, LocalDateTime createdAt) {
        this.userBorrowId = userBorrowId;
        this.groupId = groupId;
        this.accountBorrowId = accountBorrowId;
        this.loanAmount = loanAmount;
        this.term = term;
        this.intRate = intRate;
        this.createdAt = createdAt;
    }
}
