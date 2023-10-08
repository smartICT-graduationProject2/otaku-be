package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.CodeInputRequestDto;
import com.otaku.otakube.dto.event.request.EventEnterRequestDto;
import com.otaku.otakube.service.log.EventLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-logs")
public class EventLogController {

    private final EventLogService eventLogService;

    //이벤트 참여하기
    @PostMapping("")
    public void enterEvent(EventEnterRequestDto request) {
        eventLogService.enterEvent(request);
    }

    //입장 코드 입력
    @PatchMapping("")
    public Boolean inputCode(CodeInputRequestDto request) {
        return eventLogService.inputCode(request);
    }
}
