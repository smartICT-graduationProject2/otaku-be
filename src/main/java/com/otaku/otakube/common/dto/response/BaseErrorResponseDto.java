package com.otaku.otakube.common.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.constants.ResponseDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"code", "status", "message", "timestamp"})
public class BaseErrorResponseDto implements ResponseDetails {
    @Schema(description = "응답 코드", defaultValue = "2000", example = "2000")
    private final int code;
    @Schema(description = "응답 상태", defaultValue = "400", example = "400")
    private final int status;
    @Schema(description = "응답 메시지", defaultValue = "잘못된 요청입니다.", example = "잘못된 요청입니다.")
    private final String message;

    private final LocalDateTime timestamp;

    private BaseErrorResponseDto(ErrorDetails errorDetails) {
        this.code = errorDetails.getCode();
        this.status = errorDetails.getStatus();
        this.message = errorDetails.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    private BaseErrorResponseDto(ErrorDetails responseDetails, String message) {
        this.code = responseDetails.getCode();
        this.status = responseDetails.getStatus();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static BaseErrorResponseDto of(ErrorDetails errorDetails) {
        return new BaseErrorResponseDto(errorDetails);
    }

    public static BaseErrorResponseDto of(ErrorDetails errorDetails, String message) {
        return new BaseErrorResponseDto(errorDetails, message);
    }
}
