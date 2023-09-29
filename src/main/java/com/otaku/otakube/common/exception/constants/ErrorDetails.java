package com.otaku.otakube.common.exception.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ErrorDetails implements ResponseDetails {


    /**
     * 1000: 요청 성공
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청이 성공하였습니다."),
    CREATED(1001, HttpStatus.CREATED.value(), "요청이 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    NOT_FOUND(2001, HttpStatus.NOT_FOUND.value(), "요청한 URI를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(2002, HttpStatus.METHOD_NOT_ALLOWED.value(), "해당 URI에서 지원하지 않는 HTTP Method입니다."),
    UNSUPPORTED_MEDIA_TYPE(2003, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "지원하지 않는 Media Type입니다."),
    TOO_MANY_REQUESTS(2004, HttpStatus.TOO_MANY_REQUESTS.value(), "요청 횟수가 너무 많습니다. 잠시 후 다시 시도해주세요."),
    INVALID_INPUT_PARAMETER(2005, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Parameter를 확인해주세요."),
    INVALID_INPUT_BODY(2006, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Body의 형식이 일치하는 지 확인해주세요."),
    INVALID_INPUT_ENUM(2007, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Value 값으로 준 ENUM이 유효한 지 확인해주세요."),
    INVALID_FILE(2008, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 파일입니다."),
    IMAGE_UPLOAD_FAILED(2009, HttpStatus.BAD_REQUEST.value(), "이미지 업로드에 실패했습니다."),

    /**
     * 3000: 권한 오류
     */
    UNAUTHORIZED(3000, HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 사용자입니다."),
    FORBIDDEN(3001, HttpStatus.FORBIDDEN.value(), "권한이 없습니다."),
    INVALID_TOKEN(3002, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(3003, HttpStatus.BAD_REQUEST.value(), "만료된 토큰입니다."),
    INVALID_REFRESH_TOKEN(3004, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 리프레시 토큰입니다."),
    UNAUTHORIZED_ROLE(3005, HttpStatus.FORBIDDEN.value(), "인가되지 않는 사용자입니다."),
    EXPIRED_REFRESH_TOKEN(3006, HttpStatus.BAD_REQUEST.value(), "만료된 리프레시 토큰입니다."),

    /**
     * 4000: 서버 오류
     */
    INTERNAL_SERVER_ERROR(4000, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류가 발생했습니다."),
    JSON_PROCESSING_ERROR(4001, HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON 처리 중 오류가 발생했습니다."),
    INVALID_ROLE_ERROR(4002, HttpStatus.INTERNAL_SERVER_ERROR.value(), "유효하지 않은 ROLE입니다. 사용자, 기관, 관리자 여부를 다시 확인해주세요."),
    IPFS_CONNECTION_ERROR(4003, HttpStatus.INTERNAL_SERVER_ERROR.value(), "IPFS 연결 중 오류가 발생했습니다."),

    ;


    private final int code;

    private final int status;

    private final String message;


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}