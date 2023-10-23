package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.event.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Schema(name = "호스트의 이벤트 조회 응답 dto")
@Builder
public record EventHostResponseDto(
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 대표 이미지")
        String featuredImage,

        @Schema(description = "이벤트 제목")
        String name,

        @Schema(description = "이벤트 개최자 이름")
        String xNickname,

        @Schema(description = "이벤트 개최자 아이디")
        String xId,

        @Schema(description = "이벤트 주인공 이름")
        String subjectName,

        @Schema(description = "입장 코드")
        Integer code,

        @Schema(description = "이벤트 상태")
        EventStatus status
){
}
