package com.otaku.otakube.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "마이페이지 조회 입장권 응답 dto")
public record MyPageResponseDto (
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 제목")
        String name,

        @Schema(description = "이벤트 개최일")
        LocalDate openedDate
){
}
