package com.otaku.otakube.dto.support.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(name = "이벤트 후원 등록 DTO 입니다.")
public record SupportRegisterRequestDto(
        @Schema(description = "후원자의 계좌번호")
        @Size(min = 1, max = 100)
        String accountAddress,
        @Schema(description = "예금주")
        @Size(min = 1, max = 100)
        String accountHolder,
        @Schema(description = "후원 금액, 0원 초과부터 가능합니다.")
        @Positive
        @NotNull
        Long supportAmount
){
}
