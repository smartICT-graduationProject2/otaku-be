package com.otaku.otakube.service.approval;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.approval.response.ApprovalResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.approval.ApprovalRepository;
import com.otaku.otakube.service.event.EventValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalReadService {
    private final ApprovalRepository approvalRepository;
    private final EventValidateService eventValidateService;
    private final AuthInfoHelper authInfoHelper;

    @Transactional(readOnly = true)
    public List<ApprovalResponseDto> findApprovalByEvent(final Long eventId){
        User hostUser = authInfoHelper.getUser();
        eventValidateService.existEventByUserId(eventId, hostUser.getUserId());
        return approvalRepository.findApprovalByEventId(eventId);
    }

    @Transactional(readOnly = true)
    public Approval findApprovalById(final Long approvalId){
        return approvalRepository.findApprovalByApprovalIdAndStatusIsLike(approvalId, ApprovalStatus.RECEPTION)
                .orElseThrow( () -> CustomException.of(ErrorDetails.APPROVAL_NOT_FOUND));
    }

}
