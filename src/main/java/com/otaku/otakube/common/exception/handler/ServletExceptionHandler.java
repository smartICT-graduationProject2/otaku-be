package com.otaku.otakube.common.exception.handler;


import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.otaku.otakube.common.exception.constants.ErrorDetails.*;

@Slf4j
@RestControllerAdvice
public class ServletExceptionHandler {

    // MethodNotAllowed 예외 처리 (405)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BaseErrorResponseDto> handleMethodNotAllowedException(final Exception e) {
        log.error("Code: {}, Status: {}, Message: {}, Cause: {}", METHOD_NOT_ALLOWED.getCode(), METHOD_NOT_ALLOWED.getStatus(), METHOD_NOT_ALLOWED.getMessage(), e.getMessage());
        return ResponseEntity.status(METHOD_NOT_ALLOWED.getStatus()).body(BaseErrorResponseDto.of(METHOD_NOT_ALLOWED));
    }

    // UnsupportedMediaType 예외 처리 (415)
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<BaseErrorResponseDto> handleUnsupportedMediaTypeException(final Exception e) {
        log.error("Code: {}, Status: {}, Message: {}, Cause: {}", UNSUPPORTED_MEDIA_TYPE.getCode(), UNSUPPORTED_MEDIA_TYPE.getStatus(), UNSUPPORTED_MEDIA_TYPE.getMessage(), e.getMessage());
        return ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE.getStatus()).body(BaseErrorResponseDto.of(UNSUPPORTED_MEDIA_TYPE));
    }

    // InternalServerError 예외 처리 (500)
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<BaseErrorResponseDto> handleInternalServerError(final Exception e) {
        log.error("Code: {}, Status: {}, Message: {}, Cause: {}", INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage(), e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR.getStatus()).body(BaseErrorResponseDto.of(INTERNAL_SERVER_ERROR));
    }
}
