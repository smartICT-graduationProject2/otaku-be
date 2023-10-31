package com.otaku.otakube.service.approval;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.repository.approval.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalValidateService {
    private final ApprovalRepository approvalRepository;

    @Transactional(readOnly = true)
    public void existsApproval(final Long eventId, final Long applicantId){
        if (approvalRepository.existsApproval(eventId, applicantId))
            throw CustomException.of(ErrorDetails.APPROVAL_ALREADY_EXISTS);
    }

    @Transactional(readOnly = true)
    public void validateApprovalPermission(final Long approvalId, final Long hostId){
        if (!approvalRepository.validateApprovalPermission(approvalId, hostId))
            throw CustomException.of(ErrorDetails.APPROVAL_ALREADY_EXISTS);
    }
}
