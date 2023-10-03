package com.otaku.otakube.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserRefreshTokensRequestDto(
        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTUwMDU2NDEsImV4cCI6MjY5NTAwOTI0MSwic3ViIjoiMyIsIlJPTEUiOiJST0xFX1VTRVIifQ.7EFADZ1FgKzSi9LEK1QPxA0iebPg1cyX6eOCfIvvfVo")
        @NotBlank(message = "리프레시 토큰은 필수 입력값입니다.")
        String refreshToken
) {
}
