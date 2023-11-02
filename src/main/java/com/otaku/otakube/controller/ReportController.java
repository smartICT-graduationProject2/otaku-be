package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.report.response.ReportResponseDto;
import com.otaku.otakube.service.report.ReportReadService;
import com.otaku.otakube.service.report.ReportUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Report", description = "신고 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
@Validated
public class ReportController {
    private final ReportReadService reportReadService;
    private final ReportUpdateService reportUpdateService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<BaseResponseDto<List<ReportResponseDto>>> getReportTable() {
        return BaseResponseDto.success(reportReadService.readAllReport());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{reportId}")
    public ResponseEntity<BaseResponseDto> updateReportStatus(
            @ParameterObject @RequestParam(name = "inspectionResult") final Boolean inspectionResult,
            @PathVariable final Long reportId
    ) {
        reportUpdateService.updateReport(reportId, inspectionResult);
        return BaseResponseDto.success();
    }
}
