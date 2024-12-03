package com.billit.loan_service.kafka.consumer;

import com.billit.common.event.LoanStatusUpdateEvent;
import com.billit.loan_service.dto.LoanSuccessStatusRequestDto;
import com.billit.loan_service.service.LoanService;
import com.billit.loan_service.service.LoanStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanStatusUpdateConsumer {
    private final LoanService loanService;
    private final LoanStatusService loanStatusService;

    @KafkaListener(
            topics = "loan-status-update",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "loanStatusUpdateEventKafkaListenerContainerFactory"
    )
    public void consumeLoanStatusUpdate(LoanStatusUpdateEvent event) {
        try {
            log.info("Processing loan status update for groupId: {}", event.getGroupId());
            List<LoanSuccessStatusRequestDto> statusUpdateRequests = event.getGroupLoans()
                    .stream()
                    .map(loan -> new LoanSuccessStatusRequestDto(
                            loan.getLoanId(),
                            1,
                            event.getIssueDate()
                    ))
                    .toList();

            statusUpdateRequests.forEach(loanStatusService::updateLoanStatusSuccess);
            log.info("Successfully processed loan status update for groupId: {}", event.getGroupId());
        } catch (Exception e) {
            log.error("Error processing loan status update for groupId: {}", event.getGroupId(), e);
            throw e;
        }
    }
}
