package com.billit.loan_service.kafka.compensation.handler;

import com.billit.loan_service.entity.LoanStatus;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.kafka.compensation.event.LoanStatusCompensationEvent;
import com.billit.loan_service.repository.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanStatusCompensationHandler {
    private final LoanStatusRepository loanStatusRepository;

    @KafkaListener(topics = "loan-status-compensation")
    public void handleLoanStatusCompensation(LoanStatusCompensationEvent event) {
        try {
            LoanStatus status = loanStatusRepository.findByLoanId(event.getLoanId());
            if (status != null) {
                status.updateStatus(LoanStatusType.WAITING);
                status.getLoan().updateIssueDate(null);
                loanStatusRepository.save(status);
            }
            log.info("Successfully reverted loan status for loanId: {}", event.getLoanId());
        } catch (Exception e) {
            log.error("Failed to revert loan status", e);
            throw e;
        }
    }
}
