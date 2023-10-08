package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.EventFindRequestDto;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.AdmissionResponseDto;
import com.otaku.otakube.dto.event.response.EventDetailFindResponseDto;
import com.otaku.otakube.dto.event.response.EventFindResponseDto;
import com.otaku.otakube.dto.event.response.HostEventsFindResponseDto;
import com.otaku.otakube.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    //이벤트 조회
    @GetMapping("")
    public List<EventFindResponseDto> findEvents(EventFindRequestDto request) {
        return eventService.findEvents(request);
    }

    //이벤트 등록
    @PostMapping("")
    public void saveEvent(EventSaveRequestDto request) {
        eventService.saveEvent(request);
    }

    //이벤트 상세 조회
    @GetMapping("/{eventId}")
    public EventDetailFindResponseDto findEventDetail(@PathVariable Long eventId) {
        return eventService.findEventDetail(eventId);
    }

    //이벤트 신고
    @PostMapping("/{eventId}/report")
    public void reportEvent(@PathVariable Long eventId) {
        eventService.reportEvent(eventId);
    }

    //입장권 조회
    @GetMapping("/admission-ticket")
    public AdmissionResponseDto findAdmission(Long eventId) {
        return eventService.findAdmission(eventId);
    }

    //이벤트 특전 이미지 조회
    @GetMapping("/{eventId}/perks_image")
    public String findPerksImage(@PathVariable Long eventId) {
        return eventService.findPerksImage(eventId);
    }

    //개최자의 이벤트 전체 조회
    @GetMapping("/host")
    public List<HostEventsFindResponseDto> findHostEvents(Long userId) {
        return eventService.findHostEvents(userId);
    }
}
