package com.otaku.otakube.dto.event.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


/**
 * 이벤트 등록 Response
 */
@Schema(name = "이벤트 등록 응답 dto")
@Builder
public record EventSaveResponseDto(
        @Schema(description = "이벤트 대상 아이디")
        @NotNull
        Long eventId
){
}
