package com.otaku.otakube.service.log;

import com.otaku.otakube.dto.event.request.EventSupportRequestDto;
import com.otaku.otakube.dto.event.response.SupporterFindResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.log.SupportLog;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.log.SupportLogRepository;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SupportService {

    private final SupportLogRepository supportLogRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    /**
     * 이벤트 후원
     */
    @Transactional
    public void supportEvent(EventSupportRequestDto request) {

        User supporter = userRepository.findById(request.getSupporterId()).get();

        Event event = eventRepository.findById(request.getEventId()).get();
        Support support = event.getSupport();

        SupportLog supportLog = new SupportLog(supporter, request.getAuthUrl(), request.getSupportAmount(), support);
        supportLogRepository.save(supportLog);
    }

    /**
     * 개최자의 후원자 조회
     */
    public List<SupporterFindResponseDto> findSupporters(Long eventId) {

        Event event = eventRepository.findById(eventId).get();

        Long supportId = event.getSupport().getSupportId();

        return supportLogRepository.findSupporters(supportId);
    }

    /**
     * 이벤트 후원 처리
     */
    @Transactional
    public void approveSupport(Long supportLogId, Boolean isRight) {

        SupportLog supportLog = supportLogRepository.findById(supportLogId).get();

        supportLog.changeStatus(isRight);
    }
}
