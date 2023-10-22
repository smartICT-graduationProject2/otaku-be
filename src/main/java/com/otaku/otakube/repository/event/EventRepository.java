package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByEventIdAndStatusNotIn(Long eventId, List<EventStatus> statuses);
}
