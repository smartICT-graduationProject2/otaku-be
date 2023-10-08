package com.otaku.otakube.repository.log;

import com.otaku.otakube.dto.event.response.MyPageEventsResponseDto;
import com.otaku.otakube.entity.log.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    @Query("select el from EventLog el where el.user.userId = :userId and el.event.eventId = :eventId")
    EventLog findEventLog(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Query("select new com.otaku.otakube.dto.event.response.MyPageEventsResponseDto" +
            "(e.eventId, e.name, e.createdAt)" +
            " from EventLog el join el.event e" +
            " where el.user.userId = :userId")
    List<MyPageEventsResponseDto> findMyPageEvents(@Param("userId") Long userId);

    @Query("select new com.otaku.otakube.dto.event.response.MyPageEventsResponseDto" +
            "(e.eventId, e.name, e.createdAt)" +
            " from EventLog el join el.event e" +
            " where el.user.userId = :userId and el.status = 'ACTIVE'")
    List<MyPageEventsResponseDto> findMyPagePerks(@Param("userId") Long userId);
}
