package com.otaku.otakube.service.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.EventStatus;
import com.otaku.otakube.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReadService {

    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public Event findEventById(final Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Event findEventByIdAndStatus(final Long eventId) {
        return eventRepository.findByEventIdAndStatusNotIn(eventId, List.of(EventStatus.DELETED, EventStatus.CLOSED))
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }
}
