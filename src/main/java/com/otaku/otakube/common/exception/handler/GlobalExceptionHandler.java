package com.otaku.otakube.common.exception.handler;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.exception.custom.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<BaseErrorResponseDto> handleCustomException(final CustomException e) {
        log.error("Code: {}, Status: {}, Message: {}, Cause: {}", e.getResponseDetails().getCode(), e.getResponseDetails().getStatus(), e.getResponseDetails().getMessage(), e.getMessage());


        return ResponseEntity
                .status(e.getResponseDetails().getStatus())
                .body(BaseErrorResponseDto.of(e.getResponseDetails()));
    }
}
