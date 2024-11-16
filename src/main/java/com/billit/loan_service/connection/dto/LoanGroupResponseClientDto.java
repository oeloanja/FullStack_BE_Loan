package com.billit.loan_service.connection.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanGroupResponseClientDto {
    private Integer groupId;
    private Boolean isFulled;

    @Builder
    public LoanGroupResponseClientDto(Integer groupId, Boolean isFulled) {
        this.groupId = groupId;
        this.isFulled = isFulled;
    }
}
