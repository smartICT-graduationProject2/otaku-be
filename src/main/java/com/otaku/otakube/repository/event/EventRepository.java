package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventFindResponseDto;
import com.otaku.otakube.dto.event.response.HostEventsFindResponseDto;
import com.otaku.otakube.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    //오늘의 이벤트 조회
    @Query("select new com.otaku.otakube.dto.event.response.EventFindResponseDto" +
            "(e.eventId, e.name, e.xNickname, e.xId, sj.name, sp.currentAmount, sp.status)" +
            " from Event e join e.subject sj" +
            " join e.support sp")
    List<EventFindResponseDto> findTodayEvents();

    //개최자의 이벤트 전체 조회
    @Query("select new com.otaku.otakube.dto.event.response.HostEventsFindResponseDto" +
            "(e.name, e.xNickname, e.xId, sj.name, e.code)" +
            " from Event e join e.subject sj" +
            " where e.user.userId = :userId")
    List<HostEventsFindResponseDto> findHostEvents(@Param("userId") Long userId);
}
