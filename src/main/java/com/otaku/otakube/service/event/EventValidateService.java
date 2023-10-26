package com.otaku.otakube.service.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.entity.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventValidateService {
    private final EventReadService eventReadService;

    @Transactional(readOnly = true)
    public Event getEventByPublicStatus(final Long eventId, final Boolean isPublic) {
        Event eventForValidation = eventReadService.findEventForEventLog(eventId);

        if (isPublic.equals(eventForValidation.getIsPublic()))
            throw CustomException.of(ErrorDetails.INVALID_EVENT_RANGE);

        return eventForValidation;
    }
}
