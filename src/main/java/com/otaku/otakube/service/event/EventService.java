package com.otaku.otakube.service.event;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.EventStatus;
import com.otaku.otakube.entity.event.Subject;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.log.WishListRepository;
import com.otaku.otakube.repository.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final SubjectRepository subjectRepository;

    private final AuthInfoHelper authInfoHelper;

    /**
     * 이벤트 등록
     */
    @Transactional
    public void saveEvent(EventSaveRequestDto request) {

        User user = authInfoHelper.getUser();

        //임시 입장코드 생성
        Integer code = (int) (Math.random() * 10000);

        //대상 찾기
        Subject subject = subjectRepository.findById(request.subjectId()).get();

        //이벤트 생성
        Event event = new Event(request.name(), request.address(), code, null,
                request.description(), request.isPublic(), request.xNickname(), request.xId(),
                null, request.openedDate(), request.closedDate(), EventStatus.PREPARATION,
                user, subject, null);

        //이벤트 저장
        eventRepository.save(event);
    }
}
