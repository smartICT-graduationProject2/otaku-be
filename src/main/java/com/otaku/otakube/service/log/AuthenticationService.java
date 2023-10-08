package com.otaku.otakube.service.log;

import com.otaku.otakube.dto.event.response.AuthenticationFindResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.Authentication;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.log.AuthenticationRepository;
import com.otaku.otakube.repository.log.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final EventLogRepository eventLogRepository;

    /**
     * 개최자의 참여자 조회
     */
    public List<AuthenticationFindResponseDto> findAuthentication(Long eventId) {
        return authenticationRepository.findAuthentications(eventId);
    }

    /**
     * 개최자의 참여자 처리
     */
    @Transactional
    public void approveAuthentication(Long authenticationId, Boolean isRight) {

        Authentication authentication = authenticationRepository.findById(authenticationId).get();

        User user = authentication.getUser();
        Event event = authentication.getEvent();

        EventLog eventLog = eventLogRepository.findEventLog(user.getUserId(), event.getEventId());

        eventLog.changeStatus(EventLogStatus.EXPECTED);

        authentication.changeStatus(isRight);
    }
}
