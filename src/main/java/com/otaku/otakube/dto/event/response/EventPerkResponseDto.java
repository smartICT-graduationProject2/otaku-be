package com.otaku.otakube.dto.event.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "이벤트 특전 응답 dto")
public record EventPerkResponseDto(
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 이름")
        String name,

        @Schema(description = "이벤트 대표 이미지")
        String perksImage
) {
}
