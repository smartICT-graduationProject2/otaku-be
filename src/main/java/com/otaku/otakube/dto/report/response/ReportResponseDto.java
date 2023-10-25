package com.otaku.otakube.dto.report.response;

import lombok.Builder;

@Builder
public record ReportResponseDto(
        Long hostInspectionId,
        String authUrl
){
}
