package com.otaku.otakube.service.approval;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.repository.approval.ApprovalRepository;
import com.otaku.otakube.service.eventlog.EventLogUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalUpdateService {
    private final ApprovalRepository approvalRepository;
    private final ApprovalReadService approvalReadService;
    private final ApprovalValidateService approvalValidateService;
    private final EventLogUpdateService eventLogUpdateService;
    private final AuthInfoHelper authInfoHelper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateApprovalStatus(final Long approvalId){
        Approval approvalForUpdating = approvalReadService.findApprovalById(approvalId);
        Long hostId = authInfoHelper.getUser().getUserId();
        updateApprovalToApprove(approvalId, hostId, approvalForUpdating);
        eventLogUpdateService.updateEventLogToApprove(approvalId);
    }

    private void updateApprovalToApprove(Long approvalId, Long hostId, Approval approvalForUpdating) {
        approvalValidateService.validateApprovalPermission(approvalId, hostId);
        approvalForUpdating.approvedApplicant();
        approvalRepository.save(approvalForUpdating);
    }

}
