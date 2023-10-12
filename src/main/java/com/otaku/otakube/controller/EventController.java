package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.EventInquiryRequestDto;
import com.otaku.otakube.dto.event.response.EventInquiryResponseDto;
import com.otaku.otakube.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    //이벤트 조회
    @GetMapping("")
    public List<EventInquiryResponseDto> findEvents(EventInquiryRequestDto request) {
        return eventService.findEvents(request);
    }

    //이벤트 조회
    @GetMapping("/{eventId}")
    public List<EventInquiryResponseDto> findEvents(@PathVariable final Long eventId) {
        return eventService.findEvents(request);
    }
}
