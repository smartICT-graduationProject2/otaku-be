package com.otaku.otakube.service.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventReadService {

    private final EventRepository eventRepository;

    /**
     * 이벤트 등록
     */
    @Transactional
    public Event findEventById(final Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }
}
