package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.EventSupportRequestDto;
import com.otaku.otakube.service.log.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supports")
public class SupportController {

    private final SupportService supportService;

    @PostMapping("")
    public void supportEvent(EventSupportRequestDto request) {

        supportService.supportEvent(request);
    }
}
