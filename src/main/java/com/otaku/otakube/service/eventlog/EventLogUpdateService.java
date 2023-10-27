package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.event.response.EventPerkResponseDto;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.eventlog.EventLogRepository;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogUpdateService {
    private final EventLogRepository eventLogRepository;
    private final EventLogReadService eventLogReadService;
    private final EventReadService eventReadService;
    private final AuthInfoHelper authInfoHelper;

    @Transactional
    public EventPerkResponseDto updateEventLogStatus(final Long eventId, final Integer code){
        User applicant = authInfoHelper.getUser();

        EventLog eventLogForUpdating = eventLogReadService.findEventLog(eventId, applicant.getUserId());

        if (!eventLogForUpdating.getEvent().getCode().equals(code))
            throw CustomException.of(ErrorDetails.EVENT_CODE_NOT_MATCH);

        eventLogForUpdating.participateEvent();

        eventLogRepository.save(eventLogForUpdating);

        return eventReadService.findEventPerk(eventId);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateEventLogToApprove(Long approvalId) {
        EventLog eventLogForUpdating =  eventLogReadService.findEventLogByApproval(approvalId);
        eventLogForUpdating.approvedEvent();
        eventLogRepository.save(eventLogForUpdating);
    }
}
