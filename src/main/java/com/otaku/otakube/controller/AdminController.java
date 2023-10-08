package com.otaku.otakube.controller;

import com.otaku.otakube.service.event.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins")
public class AdminController {

    private final ReportService reportService;

    @PatchMapping("")
    public void approveReport(Long eventId, Boolean isApproved) {
        reportService.approveReport(eventId, isApproved);
    }
}
