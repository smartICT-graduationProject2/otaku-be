package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
}
