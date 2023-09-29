package com.otaku.otakube.common.exception.custom;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import lombok.Getter;

/**
 * {@code CustomException}는 {@code RuntimeException}을 상속받아 구현한 커스텀 예외 클래스입니다. <br>
 * static of 메소드를 통해 {@code ErrorDetails}를 받아 {@code CustomException}을 생성합니다. <br>
 * <p>
 * e.g. {@code throw CustomException.of(ErrorDetails)}
 */

@Getter
public class CustomException extends RuntimeException {
    protected final ErrorDetails responseDetails;

    public CustomException(ErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.responseDetails = errorDetails;
    }

    public static CustomException of(ErrorDetails errorDetails) {
        return new CustomException(errorDetails);
    }
}
