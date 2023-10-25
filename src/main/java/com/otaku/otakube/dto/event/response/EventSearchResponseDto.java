package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.event.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Schema(name = "이벤트 조회 응답 dto")
@Builder
public record EventSearchResponseDto(
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

        @Schema(description = "모금 상태")
        EventStatus status,

        @Schema(description = "좋아요 상태")
        Boolean wishList
){
}
