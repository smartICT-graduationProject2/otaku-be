package com.otaku.otakube.service.event;

import com.otaku.otakube.dto.event.request.EventInquiryRequestDto;
import com.otaku.otakube.dto.event.response.EventInquiryResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.log.WishListRepository;
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
    private final WishListRepository wishListRepository;

    /**
     * 이벤트 조회
     * isWishList=false면 오늘의 이벤트, true면 memberId의 관심 이벤트
     * subject=백현 -> 주인공 이름이 "백현"을 포함하는 이벤트들을 조회
     */
    public List<EventInquiryResponseDto> findEvents(EventInquiryRequestDto request) {

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

    public void saveEvent(Event event) {

    }
}
