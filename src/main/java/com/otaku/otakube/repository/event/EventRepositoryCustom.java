package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventRepositoryCustom {
    Slice<EventListResponseDto> findEventListBySubjectId(Pageable pageable, Long subjectId, Long userId);
}
