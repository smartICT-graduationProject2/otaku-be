package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.approval.request.ApprovalRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.eventlog.EventLogRepository;
import com.otaku.otakube.repository.approval.ApprovalRepository;
import com.otaku.otakube.service.approval.ApprovalValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogCreateService {
    private final EventLogRepository eventLogRepository;
    private final EventLogValidateService eventLogValidateService;
    private final AuthInfoHelper authInfoHelper;
    private final ApprovalRepository approvalRepository;
    private final ApprovalValidateService approvalValidateService;

    @Transactional
    public void createExpectedEventLog(final Long eventId){
        User applicant = authInfoHelper.getUser();

        Event eventForCreatingEventLog = eventLogValidateService.existsEventLog(eventId, applicant.getUserId(), false);

        eventLogRepository.save(
                EventLog.builder()
                        .event(eventForCreatingEventLog)
                        .user(applicant)
                        .status(EventLogStatus.EXPECTED)
                        .build()
        );
    }

    @Transactional
    public void createPreAuthEventLog(final Long eventId, ApprovalRequestDto request){
        User applicant = authInfoHelper.getUser();

        Event eventForCreatingEventLog = eventLogValidateService.existsEventLog(eventId, applicant.getUserId(), true);
        approvalValidateService.existsApproval(eventId, applicant.getUserId());

        eventLogRepository.save(
                EventLog.builder()
                        .event(eventForCreatingEventLog)
                        .user(applicant)
                        .status(EventLogStatus.PREAUTH)
                        .build()
        );

        approvalRepository.save(
                Approval.builder()
                        .xNickname(request.xNickname())
                        .xId(request.xId())
                        .applicant(applicant)
                        .event(eventForCreatingEventLog)
                        .build()
        );
    }
}
