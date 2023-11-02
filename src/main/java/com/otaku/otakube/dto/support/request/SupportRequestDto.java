package com.otaku.otakube.dto.support.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(name = "사용자의 후원 등록 dto")
public record SupportRequestDto (
        @Schema(description = "은행명")
        @Size(min = 1, max = 100)
        String bank,
        @Schema(description = "계좌")
        @Size(min = 1, max = 100)
        String accountAddress,
        @Schema(description = "예금주")
        @Size(min = 1, max = 100)
        String accountHolder,
        @Schema(description = "목표 금액, 0원 초과부터 가능합니다.")
        @Positive
        @NotNull
        Long targetAmount
){
}
