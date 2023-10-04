package com.otaku.otakube.service.event;

import com.otaku.otakube.dto.event.request.EventInquiryRequestDto;
import com.otaku.otakube.dto.event.response.EventInquiryResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Subject;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.log.WishList;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTest {

    @Autowired
    EventService eventService;
    @Autowired private EntityManager em;

    @BeforeEach
    public void initDate() {
        User member1 = new User("김진원", "jinwon@gmail.com");
        User member2 = new User("유정현", "joenghyeon@gmail.com");
        User member3 = new User("이윤진", "yoonjin@gmail.com");
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        Subject subject1 = new Subject("아이돌", "EXO 백현");
        Subject subject2 = new Subject("아이돌", "변백현");
        Subject subject3 = new Subject("배우", "이병헌");
        Subject subject4 = new Subject("애니", "뚱이");
        em.persist(subject1);
        em.persist(subject2);
        em.persist(subject3);
        em.persist(subject4);

        Event event1 = new Event("EXO 백현 행사1", "건대 새천년관", "백현 이미지", "설명", false, "트위터 닉네임2", "트위터 아이디2", "특전 이미지", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-26"), member2, subject1);
        Event event2 = new Event("변백현 행사", "건대 공학관", "백현 이미지", "설명", false, "트위터 닉네임3", "트위터 아이디3", "특전 이미지", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-27"), member3, subject2);
        Event event3 = new Event("EXO 백현 행사2", "건대 신공학관", "백현 이미지", "설명", false, "트위터 닉네임2", "트위터 아이디2", "특전 이미지", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-28"), member2, subject1);
        Event event4 = new Event("이병헌 행사", "건대 도서관", "이병헌 이미지", "설명", false, "트위터 닉네임1", "트위터 아이디1", "특전 이미지", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-29"), member1, subject3);
        Event event5 = new Event("뚱이 행사", "건대 경영관", "뚱이 이미지", "설명", true, "트위터 닉네임1", "트위터 아이디1", "특전 이미지", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-30"), member1, subject4);
        em.persist(event1);
        em.persist(event2);
        em.persist(event3);
        em.persist(event4);
        em.persist(event5);

        Support support1 = new Support(10000L, 0L, "22-22", "유정현", event1);
        Support support2 = new Support(20000L, 0L, "33-33", "이윤진", event2);
        Support support3 = new Support(30000L, 0L, "22-22", "유정현", event3);
        Support support4 = new Support(40000L, 0L, "11-11", "김진원", event4);
        Support support5 = new Support(50000L, 0L, "11-11", "김진원", event5);
        em.persist(support1);
        em.persist(support2);
        em.persist(support3);
        em.persist(support4);
        em.persist(support5);

        WishList wishList1 = new WishList(member1, event2);
        WishList wishList2 = new WishList(member1, event4);
        em.persist(wishList1);
        em.persist(wishList2);
    }

    @Test
    public void 오늘의_이벤트_조회() {

        EventInquiryRequestDto todayCondition = new EventInquiryRequestDto(1L, false, null);
        EventInquiryRequestDto wishCondition = new EventInquiryRequestDto(1L, true, null);
        EventInquiryRequestDto searchCondition = new EventInquiryRequestDto(1L, false, "백현");

        List<EventInquiryResponseDto> todayEvents = eventService.findEvents(todayCondition);
        List<EventInquiryResponseDto> wishEvents = eventService.findEvents(wishCondition);
        List<EventInquiryResponseDto> searchEvents = eventService.findEvents(searchCondition);

        assertThat(todayEvents.size()).isEqualTo(5);
        assertThat(wishEvents.size()).isEqualTo(2);
        assertThat(searchEvents.size()).isEqualTo(3);
    }
}