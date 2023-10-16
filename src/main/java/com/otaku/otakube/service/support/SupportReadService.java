package com.otaku.otakube.service.support;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.support.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupportReadService {

    private final SupportRepository supportRepository;

    /**
     * 이벤트 등록
     */
    @Transactional
    public Support findSupportById(final Long supportId) {
        return supportRepository.findById(supportId)
                .orElseThrow( () -> EventException.of(ErrorDetails.SUPPORT_NOT_FOUND));
    }
}
