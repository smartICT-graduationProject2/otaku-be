package com.otaku.otakube.dto.support.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SupportRequestDto (
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