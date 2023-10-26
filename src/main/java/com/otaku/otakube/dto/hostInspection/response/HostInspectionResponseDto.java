package com.otaku.otakube.dto.hostInspection.response;

import lombok.Builder;

@Builder
public record HostInspectionResponseDto (
        Long hostInspectionId,
        String authUrl
){
}
