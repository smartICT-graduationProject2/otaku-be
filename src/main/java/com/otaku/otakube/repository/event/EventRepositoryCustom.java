package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventListResponseDto;
import com.otaku.otakube.dto.event.response.EventSearchResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventRepositoryCustom {
    Slice<EventListResponseDto> findEventListBySubjectId(Pageable pageable, Long subjectId, Long userId);

    Slice<EventSearchResponseDto> findEventListByCondition(Pageable pageable, boolean todayEvent, String query, Long userId);

    Slice<EventSearchResponseDto> findEventListByWishList(Pageable pageable, Long userId);
}
