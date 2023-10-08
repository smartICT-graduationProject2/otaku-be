package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.EventSupportRequestDto;
import com.otaku.otakube.dto.event.response.SupporterFindResponseDto;
import com.otaku.otakube.service.log.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supports")
public class SupportController {

    private final SupportService supportService;

    //이벤트 후원
    @PostMapping("")
    public void supportEvent(EventSupportRequestDto request) {

        supportService.supportEvent(request);
    }

    //개최자의 후원자 조회
    @GetMapping("")
    public List<SupporterFindResponseDto> findSupporters(Long eventId) {
        return supportService.findSupporters(eventId);
    }

    //이벤트 후원 처리
    @PatchMapping("/{supportLogId}")
    public void approveSupport(@PathVariable Long supportLogId, Boolean isRight) {
        if (isRight == null || isRight) {
            supportService.approveSupport(supportLogId, true);
        } else {
            supportService.approveSupport(supportLogId, false);
        }
    }
}
