package com.billit.loan_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(nullable = false)
    private Long userBorrowId;

    @Column(nullable = true, precision = 15, scale = 2)
    private BigDecimal loanAmount; // 대출 금액

    @Column(nullable = true)
    private Integer term; // 대출 기간

    @Column( nullable = true, precision = 5, scale = 2)
    private BigDecimal intRate; // 이자율

    @Column
    private LocalDate issueDate; // 대출금 실제 입금일

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private Long groupId;

    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private LoanStatus loanStatus;

    public void updateIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
