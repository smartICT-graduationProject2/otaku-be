package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.event.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;


@Schema(name = "이벤트 상세 조회 응답 dto")
@Builder
public record EventDetailResponseDto(
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 대표 이미지")
        String featuredImage,

        @Schema(description = "이벤트 제목")
        String name,

        @Schema(description = "이벤트 설명")
        String description,

        @Schema(description = "이벤트 개최자 이름")
        String xNickname,

        @Schema(description = "이벤트 개최자 아이디")
        String xId,

        @Schema(description = "이벤트 주인공 카테고리")
        String category,

        @Schema(description = "이벤트 주인공 이름")
        String subjectName,

        @Schema(description = "장소 주소")
        String address,

        @Schema(description = "모금 상태")
        EventStatus status,

        @Schema(description = "후원 아이디")
        Long supportId,

        @Schema(description = "현재 후원 금액")
        Long currentAmount,

        @Schema(description = "목표 후원 금액")
        Long targetAmount,

        @Schema(description = "좋아요 상태")
        Boolean wishList,

        @Schema(description = "게시 날짜")
        LocalDateTime createdDate
){
}
