package com.otaku.otakube.service.log;

import com.otaku.otakube.dto.event.request.CodeInputRequestDto;
import com.otaku.otakube.dto.event.request.EventEnterRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.Authentication;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.log.AuthenticationRepository;
import com.otaku.otakube.repository.log.EventLogRepository;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventLogService {

    private final EventLogRepository eventLogRepository;
    private final AuthenticationRepository authenticationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    /**
     * 이벤트 참여하기
     */
    public void enterEvent(EventEnterRequestDto request) {

        User user = userRepository.findById(request.getUserId()).get();
        Event event = eventRepository.findById(request.getEventId()).get();

        EventLog eventLog = new EventLog(user, event);
        Authentication authentication = new Authentication(request.getXNickname(), request.getXId(),
                user, event);

        if (event.getIsPublic()) {
        System.out.println(event.getIsPublic());
            eventLog.changeStatus(EventLogStatus.EXPECTED);
            authentication.changeStatus(true);
        }

        eventLogRepository.save(eventLog);
        authenticationRepository.save(authentication);
    }

    /**
     * 입장 코드 입력
     * 입장코드가 맞으면 true 반환, 틀리다면 false 반환
     */
    public Boolean inputCode(CodeInputRequestDto request) {

        Event event = eventRepository.findById(request.getEventId()).get();

        if (event.getCode() == null)
            return true;

        if (event.getCode().equals(request.getCode())) {
            return true;
        } else {
            return false;
        }
    }
}
