package com.otaku.otakube.service.eventlog;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.otaku.otakube.entity.log.WishList;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventLogRepository;
import com.otaku.otakube.repository.log.WishListRepository;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLogCreateService {
    private final EventLogRepository eventLogRepository;
    private final EventReadService eventReadService;
    private final AuthInfoHelper authInfoHelper;

    @Transactional
    public void CreateExpectedEventLog(final Long eventId){
        Event eventForCreatingEventLog = eventReadService.findEventByIdAndStatus(eventId);
        User user = authInfoHelper.getUser();

        eventLogRepository.save(
                EventLog.builder()
                        .event(eventForCreatingEventLog)
                        .user(user)
                        .status(EventLogStatus.EXPECTED)
                        .build()
        );
    }
}
