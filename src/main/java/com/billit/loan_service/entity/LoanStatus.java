package com.billit.loan_service.entity;

import com.billit.loan_service.dto.LoanStatusRequest;
import com.billit.loan_service.enums.LoanStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table
public class LoanStatus {
    @Id
    private Integer loanId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private LoanStatusType status;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public LoanStatus(Loan loan, LoanStatusType loanStatusType) {
        this.loan = loan;
        this.status = loanStatusType;
    }

    public void updateStatus(LoanStatusType status) {
        this.status = status;
    }
}
