package com.otaku.otakube.service.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.event.response.*;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.EventStatus;
import com.otaku.otakube.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReadService {

    private final EventRepository eventRepository;
    private final AuthInfoHelper authInfoHelper;

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

    @Transactional(readOnly = true)
    public Event findEventForEventLog(final Long eventId) {
        return eventRepository.findByEventIdAndStatusNotIn(eventId, List.of(EventStatus.DELETED, EventStatus.CLOSED, EventStatus.UNDEFINED))
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }


    @Transactional(readOnly = true)
    public EventDetailResponseDto findEventDetailInfo(final Long eventId) {
        final Long userId = authInfoHelper.getUser().getUserId();
        return eventRepository.findEventDetailInfo(eventId, userId)
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Slice<EventHostResponseDto> findEventByHost(Pageable pageable) {
        final Long hostId = authInfoHelper.getUser().getUserId();
        return eventRepository.findEventListByHostId(pageable,hostId);
    }

    @Transactional(readOnly = true)
    public EventAdmissionResponseDto findEventAdmission(final Long eventId) {
        return eventRepository.findEventAdmissionByEventId(eventId, List.of(EventStatus.DELETED, EventStatus.CLOSED))
                .orElseThrow(() -> EventException.of(ErrorDetails.INVALID_EVENT));
    }

    @Transactional(readOnly = true)
    public EventPerkResponseDto findEventPerk(final Long eventId) {
        return eventRepository.findEventPerkByEventId(eventId)
                .orElseThrow(() -> EventException.of(ErrorDetails.EVENT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Slice<EventListResponseDto> findEventListBySubjectId(Pageable pageable, final Long subjectId) {
        final Long userId = authInfoHelper.getUser().getUserId();
        return eventRepository.findEventListBySubjectId(pageable, subjectId, userId);
    }

    @Transactional(readOnly = true)
    public Slice<EventSearchResponseDto> findEventList(Pageable pageable, final boolean isWishList, final String subject) {
        final Long userId = authInfoHelper.getUser().getUserId();
        if (isWishList) return eventRepository.findEventListByWishList(pageable, userId);
        if (Objects.isNull(subject)) return eventRepository.findEventListByCondition(pageable, true, null, userId);
        return eventRepository.findEventListByCondition(pageable, false, subject, userId);
    }

}
