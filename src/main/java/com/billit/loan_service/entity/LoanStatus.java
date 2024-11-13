package com.billit.loan_service.entity;

import com.billit.loan_service.enums.LoanStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LoanStatus {
    @Id
    private Long loanId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private LoanStatusType status;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public void updateStatus(LoanStatusType status) {
        this.status = status;
    }
}
