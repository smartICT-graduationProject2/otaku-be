package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
