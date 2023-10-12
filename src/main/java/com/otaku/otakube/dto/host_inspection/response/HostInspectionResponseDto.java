package com.otaku.otakube.dto.host_inspection.response;

import lombok.Builder;

@Builder
public record HostInspectionResponseDto (
        Long hostInspectionId,
        String authUrl
){
}
