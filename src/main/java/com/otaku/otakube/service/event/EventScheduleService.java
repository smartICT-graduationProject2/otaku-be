package com.otaku.otakube.service.event;


import com.otaku.otakube.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class EventScheduleService {

    private final EventRepository eventRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markEventAsActive() {
        log.info("[ACTIVE] start with : {}", Thread.currentThread().getName());
        eventRepository.markEventAsActive();
        log.info("[ACTIVE] end with : {}", Thread.currentThread().getName());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markEventAsClosed() {
        log.info("[CLOSED] start with : {}", Thread.currentThread().getName());
        eventRepository.markEventAsClosed();
        log.info("[CLOSED] end with : {}", Thread.currentThread().getName());
    }

}
