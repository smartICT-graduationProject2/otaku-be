package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.repository.eventlog.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogReadService {
    private final EventLogRepository eventLogRepository;

    @Transactional(readOnly = true)
    public EventLog findEventLog(final Long eventId, final Long applicantId){
        return eventLogRepository.findValidEventLogByEventAndUser(eventId, applicantId)
                .orElseThrow( () -> CustomException.of(ErrorDetails.EVENT_LOG_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public EventLog findEventLogByApproval(final Long approvalId){
        return eventLogRepository.findValidEventLogByApproval(approvalId)
                .orElseThrow( () -> CustomException.of(ErrorDetails.EVENT_LOG_NOT_FOUND));
    }
}
