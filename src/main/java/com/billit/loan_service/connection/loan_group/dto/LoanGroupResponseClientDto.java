package com.billit.loan_service.connection.loan_group.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanGroupResponseClientDto {
    private Integer groupId;
    private Boolean isFulled;

    @Builder
    public LoanGroupResponseClientDto(Integer groupId, Boolean isFulled) {
        this.groupId = groupId;
        this.isFulled = isFulled;
    }
}
