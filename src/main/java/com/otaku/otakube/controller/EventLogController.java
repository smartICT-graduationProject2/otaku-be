package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.EventEnterRequestDto;
import com.otaku.otakube.service.log.EventLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-log")
public class EventLogController {

    private final EventLogService eventLogService;

    @PostMapping("")
    public void enterEvent(EventEnterRequestDto request) {
        eventLogService.enterEvent(request);
    }
}
