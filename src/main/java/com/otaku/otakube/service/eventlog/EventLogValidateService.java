package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.repository.eventlog.EventLogRepository;
import com.otaku.otakube.service.event.EventValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogValidateService {
    private final EventLogRepository eventLogRepository;
    private final EventValidateService eventValidateService;

    @Transactional(readOnly = true)
    public Event existsEventLog(final Long eventId, final Long applicantId, final Boolean isPublic){
        Event eventForCreatingEventLog = eventValidateService.getEventByPublicStatus(eventId, isPublic);
        if (eventLogRepository.existsEventLogByEventAndUser(eventId, applicantId))
            throw CustomException.of(ErrorDetails.EVENT_LOG_ALREADY_EXISTS);
        return eventForCreatingEventLog;
    }
}
