package com.billit.loan_service.service;
import com.billit.loan_service.connection.loan_group.client.LoanGroupClient;
import com.billit.loan_service.connection.loan_group.dto.LoanGroupRequestClientDto;
import com.billit.loan_service.connection.loan_group.dto.LoanGroupResponseClientDto;
import com.billit.loan_service.dto.*;
import com.billit.loan_service.entity.Loan;
import com.billit.loan_service.enums.LoanStatusType;
import com.billit.loan_service.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
//    private final CreditEvaluationClient creditEvaluationClient;
    private final LoanGroupClient loanGroupClient;
//    private final BorrowAccountClient borrowAccountClient;

    // Create
    @Transactional
    // 대출 생성 : 성공
    public LoanResponseDto createLoanSuccess(LoanRequestDto request) {
//        연동 대기
//        BorrowAccountResponse borrowAccountResponse = borrowAccountClient.getBorrowAccountId(request.getUserBorrowId());
//        CreditEvaluationResponse creditEvaluationResponse = creditEvaluationClient.getCreditEvaluation(request.getUserBorrowId());
//        LoanGroupResponse loanGroupResponse = loanGroupClient.getLoanGroup(request.getUserBorrowId());
            Loan loan = new Loan(
                    request.getUserBorrowId(),
                    null,
                    // 가상의 값입니다.
                    11,
                    //                borrowAccountResponse.getAccountBorrowId();
                    request.getLoanAmount(),
                    request.getTerm(),

                    // 가상의 값입니다.
                    new BigDecimal("12.3"),
                    //                creditEvaluationResponse.getIntRate(),
                    LocalDateTime.now(),
                    LoanStatusType.WAITING);

        loanRepository.save(loan);
        return LoanResponseDto.from(loan);
    }

    // 대출 생성 : 거절
    public LoanResponseDto createLoanReject(LoanRequestDto request){
        Loan loan = new Loan(
                request.getUserBorrowId(),
                null, null, null, null, null,
                LocalDateTime.now(),
                LoanStatusType.REJECTED
        );
        loanRepository.save(loan);
        return LoanResponseDto.from(loan);
    }

    // Read
    // 대출 이력(상태 무관) 조회
    public List<LoanResponseDto> getUserLoanHistory(Integer userBorrowId) {
        List<Loan> loans = loanRepository.findByUserBorrowId(userBorrowId);
        return loans.stream().map(LoanResponseDto::from).toList();
    }

    // 대출 유형 별 조회
    public List<LoanResponseDto> getUserLoansByStatus(Integer userBorrowId, int status) {
        LoanStatusType loanStatusType = LoanStatusType.values()[status];
        List<Loan> loans = loanRepository.findByUserBorrowIdAndLoanStatus_Status(userBorrowId, loanStatusType);
        return loans.stream().map(LoanResponseDto::from).toList();
    }

    // 특정 대출 상세정보 조회
    public LoanResponseDto getLoanById(Integer loanId) {
        return loanRepository.findById(Long.valueOf(loanId))
                .map(LoanResponseDto::from)
                .orElse(null);
    }

    // 그룹 별 대출 조회
    public List<LoanResponseDto> getLoansByGroupId(Integer groupId){
        List<Loan> loans = loanRepository.findByGroupId(groupId);
        return loans.stream().map(LoanResponseDto::from).toList();
    }

    // 계좌고유번호로 대출 있는지 여부 확인 메소드 (있으면 true, 없으면 false)
    public boolean isExistLoanByUserAccountId(Integer userAccountId){
        List<LoanStatusType> statuses = List.of(LoanStatusType.EXECUTING, LoanStatusType.WAITING, LoanStatusType.OVERDUE);
        return loanRepository.existsByAccountBorrowIdAndLoanStatus_StatusIn(userAccountId, statuses);
    }

    // Update
    // 그룹 배정 및 업데이트
    @Transactional
    public LoanGroupResponseClientDto assignGroupToLoan(Integer loanId) {
        // Loan 객체를 DB에서 조회
        Loan loan = loanRepository.findById(Long.valueOf(loanId))
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + loanId));

        // LoanGroupRequestClientDto 생성 (loanId만 포함)
        LoanGroupRequestClientDto requestDto = new LoanGroupRequestClientDto(loan.getLoanId());

        // LoanGroupClient를 사용하여 그룹 배정 API 호출
        LoanGroupResponseClientDto response = loanGroupClient.registerLoan(requestDto);

        // 응답받은 groupId로 Loan 객체의 groupId 업데이트
        loan.assignGroup(response.getGroupId());
        loanRepository.save(loan);
        return response;
    }

    // 이자율 평균 계산
    public Double calculateAverageIntRate(Integer groupId){
        return loanRepository.findAverageIntRateByGroupId(groupId);
    }

}
