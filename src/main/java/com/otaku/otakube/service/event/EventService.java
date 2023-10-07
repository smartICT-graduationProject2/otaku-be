package com.otaku.otakube.service.event;

import com.otaku.otakube.dto.event.request.EventFindRequestDto;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.EventInquiryResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Subject;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.event.SubjectRepository;
import com.otaku.otakube.repository.event.SupportRepository;
import com.otaku.otakube.repository.log.WishListRepository;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final WishListRepository wishListRepository;
    private final SubjectRepository subjectRepository;
    private final SupportRepository supportRepository;
    private final UserRepository userRepository;

    /**
     * 이벤트 조회
     * userId=1 -> user_id 1이 현재 이용중
     * isWishList=false -> 오늘의 이벤트, isWishList=true -> 현재 user_id의 관심 이벤트
     * subject=백현 -> 주인공 이름이 "백현"을 포함하는 이벤트들을 조회
     */
    public List<EventInquiryResponseDto> findEvents(EventFindRequestDto request) {

        List<EventInquiryResponseDto> todayEvents = eventRepository.findTodayEvents();
        List<Long> wishEventIds = wishListRepository.findWishEvents(request.getUserId());

        //관심 이벤트 true로 설정
        for (EventInquiryResponseDto todayEvent : todayEvents) {
            if (wishEventIds.contains(todayEvent.getEventId())) {
                todayEvent.setIsWishList(true);
            }
        }

        if (!(request.getSubject() == null)) { //검색 이벤트

            List<EventInquiryResponseDto> searchEvents = new ArrayList<>();

            for (EventInquiryResponseDto todayEvent : todayEvents) {
                if (todayEvent.getSubject().contains(request.getSubject())) {
                    searchEvents.add(todayEvent);
                }
            }

            return searchEvents;

        } else if (request.getIsWishList()) { //관심 이벤트

            List<EventInquiryResponseDto> wishEvents = new ArrayList<>();

            for (EventInquiryResponseDto todayEvent : todayEvents) {
                if (todayEvent.getIsWishList()) {
                    wishEvents.add(todayEvent);
                }
            }

            return wishEvents;
        }

        return todayEvents; //오늘의 이벤트

    }

    /**
     * 이벤트 등록
     */
    public void saveEvent(EventSaveRequestDto request) {

        Subject subject = new Subject(request.getCategory(), request.getSubject());
        subjectRepository.save(subject);

        User user = userRepository.findById(request.getUserId()).get();
        Event event = new Event(request.getName(), request.getAddress(), request.getFeaturedImage(), request.getDescription(), request.getIsPublic(), request.getXNickname(), request.getXId(), request.getPerksImage(), LocalDate.parse(request.getOpenedDate()), LocalDate.parse(request.getClosedDate()), user, subject);
        eventRepository.save(event);

        Support support = new Support(request.getTargetAmount(), 0L, request.getAccountAddress(), request.getAccountHolder(), event);
        supportRepository.save(support);
    }
}
