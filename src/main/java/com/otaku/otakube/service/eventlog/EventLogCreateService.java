package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.approval.request.ApprovalRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventLogRepository;
import com.otaku.otakube.repository.log.ApprovalRepository;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogCreateService {
    private final EventLogRepository eventLogRepository;
    private final EventReadService eventReadService;
    private final AuthInfoHelper authInfoHelper;
    private final ApprovalRepository approvalRepository;

    @Transactional
    public void createExpectedEventLog(final Long eventId){
        Event eventForCreatingEventLog = eventReadService.findEventByIdAndStatus(eventId);

        if (Boolean.TRUE.equals(eventForCreatingEventLog.getIsPublic()))
            throw CustomException.of(ErrorDetails.INVALID_EVENT_RANGE);

        User user = authInfoHelper.getUser();

        eventLogRepository.save(
                EventLog.builder()
                        .event(eventForCreatingEventLog)
                        .user(user)
                        .status(EventLogStatus.EXPECTED)
                        .build()
        );
    }

    @Transactional
    public void createPreAuthEventLog(final Long eventId, ApprovalRequestDto request){
        Event eventForCreatingEventLog = eventReadService.findEventByIdAndStatus(eventId);

        if (Boolean.TRUE.equals(eventForCreatingEventLog.getIsPublic()))
            throw CustomException.of(ErrorDetails.INVALID_EVENT_RANGE);

        User user = authInfoHelper.getUser();

        eventLogRepository.save(
                EventLog.builder()
                        .event(eventForCreatingEventLog)
                        .user(user)
                        .status(EventLogStatus.PREAUTH)
                        .build()
        );

        approvalRepository.save(
                Approval.builder()
                        .xNickname(request.xNickname())
                        .xId(request.xId())
                        .applicant(user)
                        .event(eventForCreatingEventLog)
                        .build()
        );
    }
}
