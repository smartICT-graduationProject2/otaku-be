package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventAdmissionResponseDto;
import com.otaku.otakube.dto.event.response.EventPerkResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    Optional<Event> findByEventIdAndStatusNotIn(Long eventId, List<EventStatus> statuses);

    @Query(value = """
            select new com.otaku.otakube.dto.event.response.EventAdmissionResponseDto
            (e.eventId,
            e.name,
            e.openedDate,
            e.xNickname,
            e.featuredImage)
            from Event e
            where e.eventId = :eventId and e.status not in :statuses
            """)
    Optional<EventAdmissionResponseDto> findEventAdmissionByEventId(
            @Param("eventId") final Long eventId,@Param("statuses") final List<EventStatus> statuses);


    @Query(value = """
            select new com.otaku.otakube.dto.event.response.EventPerkResponseDto
            (e.eventId,
            e.name,
            e.perksImage)
            from Event e
            where e.eventId = :eventId
            """)
    Optional<EventPerkResponseDto> findEventPerkByEventId(@Param("eventId") final Long eventId);

    @Modifying
    @Query(value = """
            UPDATE t_event e
            INNER JOIN t_event_log el ON e.event_id = el.event_id
            SET e.status = 'CLOSED', el.status = 'DELETED'
            WHERE e.closed_date < CURRENT_DATE
            """, nativeQuery = true)
    void markEventAsClosed();

}
