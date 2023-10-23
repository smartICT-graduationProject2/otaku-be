package com.otaku.otakube.dto.event.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "이벤트 입장권 응답 dto")
public record EventAdmissionResponseDto(
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 이름")
        String name,

        @Schema(description = "이벤트 개최일")
        LocalDate openedDate,

        @Schema(description = "개최자 트위터 닉네임")
        String xNickname,

        @Schema(description = "이벤트 대표 이미지")
        String featuredImage
) {
}
