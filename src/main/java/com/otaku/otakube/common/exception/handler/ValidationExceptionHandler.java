package com.otaku.otakube.common.exception.handler;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.otaku.otakube.common.exception.constants.ErrorDetails.INVALID_INPUT_BODY;
import static com.otaku.otakube.common.exception.constants.ErrorDetails.INVALID_INPUT_PARAMETER;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {
    /**
     * API Parameter Validation 중 발생한 예외를 처리합니다. <br>
     * message 값의 내용을 출력합니다.
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<BaseErrorResponseDto> handleValidationException(final ConstraintViolationException e) {
        log.error("Code: {}, Status: {}, Message: {}", INVALID_INPUT_PARAMETER.getCode(), INVALID_INPUT_PARAMETER.getStatus(), e.getMessage());


        return ResponseEntity
                .status(INVALID_INPUT_PARAMETER.getStatus())
                .body(BaseErrorResponseDto.of(INVALID_INPUT_PARAMETER, e.getMessage()));
    }

    /**
     * API Body Validation 중 발생한 예외를 처리합니다. <br>
     * message 값의 내용을 출력합니다.
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<BaseErrorResponseDto> handleValidationException(final MethodArgumentNotValidException e) {
        log.error("Code: {}, Status: {}, Message: {}", INVALID_INPUT_BODY.getCode(), INVALID_INPUT_BODY.getStatus(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());


        return ResponseEntity
                .status(INVALID_INPUT_BODY.getStatus())
                .body(BaseErrorResponseDto.of(INVALID_INPUT_BODY, e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }
}
