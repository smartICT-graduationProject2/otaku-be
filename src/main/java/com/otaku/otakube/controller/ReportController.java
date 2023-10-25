package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.report.response.ReportResponseDto;
import com.otaku.otakube.service.report.ReportReadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Report", description = "신고 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
@Validated
public class ReportController {
    private final ReportReadService reportReadService;
    @GetMapping
    public ResponseEntity<BaseResponseDto<List<ReportResponseDto>>> getHostInspectionTable() {
        return BaseResponseDto.success(reportReadService.readAllReport());
    }
}
