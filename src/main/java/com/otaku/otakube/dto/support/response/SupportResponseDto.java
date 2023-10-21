package com.otaku.otakube.dto.support.response;

import com.otaku.otakube.entity.common.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(name = "이벤트 후원 로그 조회 응답값 dto")
public record SupportResponseDto(
        @Schema(description = "후원 로그 아이디")
        @Positive
        @NotNull
        Long supportLogId,
        @Schema(description = "예금주")
        @Size(min = 1, max = 100)
        String accountHolder,
        @Schema(description = "후원 금액, 0원 초과부터 가능합니다.")
        @Positive
        @NotNull
        Long supportAmount,
        @Schema(description = "후원 승인 상태")
        ApprovalStatus status
){
}
