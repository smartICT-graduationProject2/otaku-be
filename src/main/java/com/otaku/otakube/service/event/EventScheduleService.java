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

//    @Scheduled(cron = "0 0 0 * * ?")
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void updateEventStatus() {
//        log.info("[EVENTS] start with : {}", Thread.currentThread().getName());
//        markEventAsInProgress();
//        markEventAsCompleted();
//        log.info("[EVENTS] end with : {}", Thread.currentThread().getName());
//    }
//
//    @Transactional(propagation = Propagation.MANDATORY)
//    public void markEventAsInProgress(){
//        eventRepository.updateEventStatusAsInProgress();
//    }
//
//    @Transactional(propagation = Propagation.MANDATORY)
//    public void markEventAsCompleted(){
//        eventRepository.updateEventStatusAsCompleted();
//    }

}
