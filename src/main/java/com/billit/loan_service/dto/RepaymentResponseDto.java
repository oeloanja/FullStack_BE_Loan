package com.billit.loan_service.dto;

import com.billit.loan_service.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RepaymentResponseDto {
    private UUID userBorrowId;
    private Integer accountBorrowId;

    public static RepaymentResponseDto from(Loan loan){
        return new RepaymentResponseDto(
                loan.getUserBorrowId(),
                loan.getAccountBorrowId()
        );
    }
}
